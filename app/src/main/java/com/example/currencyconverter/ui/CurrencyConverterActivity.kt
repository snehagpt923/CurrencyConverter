package com.example.currencyconverter.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.model.ConversionModel
import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.utils.DataState
import com.example.currencyconverter.utils.Utility.NUM_COLUMN
import com.example.currencyconverter.utils.Utility.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class CurrencyConverterActivity : AppCompatActivity() {

    private val viewModel: CurrencyConverterViewModel by viewModels()
    private val currencyConversionAdapter: CurrencyConversionAdapter by lazy {
        CurrencyConversionAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.converterRatesLiveData.observe(this) {
            when (it) {
                is DataState.Success<List<CurrencyRateModel>> -> {
                    displayProgressBar(false)
                    initViews(it.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        }
    }

    private fun displayError(message: String?) {
        if (message != null) {
            tvError.visibility = View.VISIBLE
            tvError.text = message
        } else {
            tvError.text = ""
            tvError.visibility = View.GONE
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun initViews(rates: List<CurrencyRateModel>) {
        val currencyRateAdapter = CurrencyRateAdapter(this, rates)

        spinner.adapter = currencyRateAdapter
        spinner.visibility = View.VISIBLE

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                convertAmount(currencyRateAdapter.getItem(position))
            }

            override fun onNothingSelected(adapter: AdapterView<*>) {
            }
        }

        with(rvConversions) {
            val gridLayoutManager = GridLayoutManager(this@CurrencyConverterActivity, NUM_COLUMN)
            layoutManager = gridLayoutManager
            adapter = currencyConversionAdapter
        }
    }

    private fun convertAmount(currencyRateModel: CurrencyRateModel) {
        when {
            currencyRateModel.currencyRate == null -> {
                Toast.makeText(this, getString(R.string.rate_not_available), Toast.LENGTH_SHORT)
                    .show()
            }
            etAmount.length() > 0 -> {
                hideKeyboard(currentFocus)
                val amount = currencyRateModel.currencyRate.times(etAmount.text.toString().toDouble())
                updateConversion(ConversionModel(currencyRateModel.currencyName, amount))
            }
            else -> {
                Toast.makeText(this, getString(R.string.enter_amount), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateConversion(conversionModel: ConversionModel) {
        with(viewModel.conversionList) {
            val existingConversion = find { it.currencyName == conversionModel.currencyName }
            if (existingConversion != null) {
                existingConversion.conversion = conversionModel.conversion
            } else {
                add(conversionModel)
            }
            currencyConversionAdapter.setData(this)
        }
    }
}