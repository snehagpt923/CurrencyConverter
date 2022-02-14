package com.example.currencyconverter.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.model.CurrencyRateModel

class CurrencyRateAdapter(
    context: Context,
    private val list: List<CurrencyRateModel>
) : ArrayAdapter<CurrencyRateModel>(context, R.layout.item_currency, list) {
    private var inflater: LayoutInflater? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return bindView(convertView, position)
    }

    private fun bindView(convertView: View?, position: Int): View {
        val holder: CurrencyRateViewHolder
        var rowView = convertView
        if (rowView == null) {
            holder = CurrencyRateViewHolder()
            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater?.inflate(R.layout.item_currency, null, false)!!
            holder.tvName = rowView.findViewById(R.id.tvName)
            rowView.tag = holder
        } else {
            holder = rowView.tag as CurrencyRateViewHolder
        }
        holder.tvName?.text = getItem(position).currencyName
        return rowView
    }

    private inner class CurrencyRateViewHolder {
        var tvName: TextView? = null
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (convertView == null) {
            view = inflater?.inflate(R.layout.item_currency, parent, false)
        }
        val item = getItem(position)
        val txtTitle = view?.findViewById(R.id.tvName) as TextView
        txtTitle.text = item.currencyName
        return view
    }

    override fun getItem(position: Int) = list[position]
}