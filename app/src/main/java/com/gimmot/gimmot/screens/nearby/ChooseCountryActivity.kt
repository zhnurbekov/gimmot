package com.gimmot.gimmot.screens.nearby


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Toast
import android.widget.AdapterView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import com.gimmot.gimmot.R
import kotlinx.android.synthetic.main.activity_choose_country.*

class ChooseCountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_country)

        var galaxies = arrayOf("Астана", "Алматы", "Москва", "Семей", "Караганды", "Уфа", "Омск", "Актау", "Россия", "Украина", "Днепр", "Актобе", "Уралск", "Кызылорда", "Сарыагаш", "Кокшетау", "Талдыкорган")

        back_btn.setOnClickListener { finish() }
        //ADAPTER
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, galaxies)
        mListView.setAdapter(adapter)

        //SEARCHBAR TEXT CHANGE LISTENER
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //SEARCH FILTER
                adapter.getFilter().filter(charSequence)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        //LISTVIEW ITEM CLICKED
        mListView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                Toast.makeText(this@ChooseCountryActivity, adapter.getItem(i)!!.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }
}