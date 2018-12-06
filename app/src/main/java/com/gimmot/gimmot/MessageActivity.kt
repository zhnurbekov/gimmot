package com.gimmot.gimmot

import android.os.Bundle
import com.gimmot.gimmot.screens.common.setupAuthGuard
import com.gimmot.gimmot.screens.common.setupBottomNavigation


class MessageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad)
        setupAuthGuard { uid ->
            setupBottomNavigation(uid, 2)
        }
    }
}