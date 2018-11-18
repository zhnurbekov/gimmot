package com.gimmot.gimmot.screens.ad

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter


class AdPageAdapter (fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return AllAdsFragment()
            1 -> return MyAdsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}