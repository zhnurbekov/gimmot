package com.gimmot.gimmot.screens.ad

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.gimmot.gimmot.BaseActivity
import com.gimmot.gimmot.R
import com.gimmot.gimmot.screens.common.setupAuthGuard
import com.gimmot.gimmot.screens.common.setupBottomNavigation
import kotlinx.android.synthetic.main.activity_ad.*

class AdActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad)
        setupAuthGuard { uid ->
            setupBottomNavigation(uid, 1)
        }
        configureTabLayout()

    }

    private fun configureTabLayout() {
        tabs_ad_main.addTab(tabs_ad_main.newTab().setText(getString(R.string.all_adds)))
        tabs_ad_main.addTab(tabs_ad_main.newTab().setText(getString(R.string.my_adds)))

        val adapter = AdPageAdapter(supportFragmentManager,
                tabs_ad_main.tabCount)
        viewpager_main.adapter = adapter

        viewpager_main.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(tabs_ad_main))
        tabs_ad_main.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager_main.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }
}