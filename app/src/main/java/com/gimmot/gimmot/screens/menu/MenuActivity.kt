package com.gimmot.gimmot.screens.menu

import android.os.Bundle
import com.gimmot.gimmot.BaseActivity
import com.gimmot.gimmot.R
import com.gimmot.gimmot.screens.common.setupAuthGuard
import com.gimmot.gimmot.screens.common.setupBottomNavigation
import com.gimmot.gimmot.screens.nearby.SearchSetupBottomDialogFragment
import kotlinx.android.synthetic.main.activity_menus.*


class MenuActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus)
        setupAuthGuard { uid ->
            setupBottomNavigation(uid, 3)
        }

        setup.setOnClickListener {
            val setupBottomDialogFragment = SetupBottomDialogFragment().newInstance()
            setupBottomDialogFragment.show(supportFragmentManager,
                    "setup_dialog")
        }
    }
}