package com.example.currencyconverter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.databinding.ItemConversionBinding
import com.example.currencyconverter.domain.model.ConversionModel

class CurrencyConversionAdapter : RecyclerView.Adapter<CurrencyConversionAdapter.ConversionHolder>() {

    private var list: List<ConversionModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionHolder {
        val itemBinding =
            ItemConversionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversionHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ConversionHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ConversionHolder(private val view: ItemConversionBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(conversionModel: ConversionModel) {
            with(view) {
                tvCurrency.text = conversionModel.currencyName
                tvAmount.text = String.format("%.2f", conversionModel.conversion)
            }
        }
    }

    fun setData(list: List<ConversionModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}