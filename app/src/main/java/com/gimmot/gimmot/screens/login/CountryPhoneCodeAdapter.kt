package com.gimmot.gimmot.screens.login

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gimmot.gimmot.R
import com.gimmot.gimmot.model.CountryPhoneCode
import kotlinx.android.synthetic.main.country_list_item.view.*


class CountryPhoneCodeAdapter (val items : List<CountryPhoneCode>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    var getCodeListener : ((String, String) -> Unit)? = null


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.country_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.counrty?.text = items.get(position).country
        holder?.phone_code?.text = items.get(position).code
        holder!!.item_container.setOnClickListener {
            getCodeListener?.invoke(holder.counrty.text.toString(),holder.phone_code.text.toString())
        }

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val counrty = view.country
    val phone_code = view.phone_code
    val item_container = view.item_container

}