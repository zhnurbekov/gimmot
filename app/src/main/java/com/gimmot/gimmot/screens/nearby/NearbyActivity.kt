package com.gimmot.gimmot.screens.nearby

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.gimmot.gimmot.BaseActivity
import com.gimmot.gimmot.R
import com.gimmot.gimmot.SpacesItemDecoration
import com.gimmot.gimmot.adapter.NearbyAdapter
import kotlinx.android.synthetic.main.activity_nearby.*


class NearbyActivity : BaseActivity(0) {
    private val TAG = "NearbyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby)
        setupBottomNavigation()

        var list = ArrayList<String>()
        list.add("Индира")
        list.add("Светлана")
        list.add("Павел")
        list.add("Сергей")
        list.add("Вика")
        list.add("Жулдыз")
        list.add("Айжан")
        list.add("Денис")
        list.add("Евгений")
        list.add("Галым")
        list.add("Стас")
        list.add("Димас")
        list.add("Денис")
        list.add("Влад")
        val adapter = NearbyAdapter(list, this)
        val manager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.font_16dp), this));
        recyclerView.setLayoutManager(manager);
        recyclerView.adapter = adapter

        setting_search.setOnClickListener{
            val searchSetupBottomDialogFragment = SearchSetupBottomDialogFragment().newInstance()
            searchSetupBottomDialogFragment.show(supportFragmentManager,
                    "add_photo_dialog_fragment")

        }

    }
}
