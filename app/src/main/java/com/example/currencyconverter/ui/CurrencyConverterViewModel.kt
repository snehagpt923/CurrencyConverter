package com.example.currencyconverter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.model.ConversionModel
import com.example.currencyconverter.domain.model.CurrencyRateModel
import com.example.currencyconverter.repository.MainRepository
import com.example.currencyconverter.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val conversionList = mutableListOf<ConversionModel>()

    private val _converterRatesLiveData: MutableLiveData<DataState<List<CurrencyRateModel>>> =
        MutableLiveData()

    init {
        getConverterRatesFromServer()
    }

    val converterRatesLiveData: LiveData<DataState<List<CurrencyRateModel>>>
        get() = _converterRatesLiveData

    private fun getConverterRatesFromServer() {
        viewModelScope.launch {
            mainRepository.getConverterRates().onEach { rates ->
                _converterRatesLiveData.value = rates
            }.launchIn(viewModelScope)
        }
    }
}