package com.gimmot.gimmot

import android.os.Bundle


class MenuActivity : BaseActivity(3) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus)
       setupBottomNavigation()
    }
}