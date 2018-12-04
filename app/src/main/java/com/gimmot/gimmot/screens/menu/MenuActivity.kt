package com.gimmot.gimmot.screens.menu

import android.os.Bundle
import com.gimmot.gimmot.BaseActivity
import com.gimmot.gimmot.R
import com.gimmot.gimmot.screens.nearby.SearchSetupBottomDialogFragment
import kotlinx.android.synthetic.main.activity_menus.*


class MenuActivity : BaseActivity(3) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus)
       setupBottomNavigation()

        setup.setOnClickListener {
            val setupBottomDialogFragment = SetupBottomDialogFragment().newInstance()
            setupBottomDialogFragment.show(supportFragmentManager,
                    "setup_dialog")
        }
    }
}