package com.gimmot.gimmot

import android.os.Bundle


class MessageActivity : BaseActivity(2) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad)
        setupBottomNavigation()
    }
}