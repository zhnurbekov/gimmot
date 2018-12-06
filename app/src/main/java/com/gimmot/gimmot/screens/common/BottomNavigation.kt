package com.gimmot.gimmot.screens.common

import android.arch.lifecycle.LifecycleObserver
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.gimmot.gimmot.BaseActivity
import com.gimmot.gimmot.MessageActivity
import com.gimmot.gimmot.R
import com.gimmot.gimmot.screens.ad.AdActivity
import com.gimmot.gimmot.screens.menu.MenuActivity
import com.gimmot.gimmot.screens.nearby.NearbyActivity
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import kotlinx.android.synthetic.main.bottom_navigation_view.*


class BottomNavigation(private val bnv: BottomNavigationViewEx,
                       private val navNumber: Int,
                       private val activity: BaseActivity): LifecycleObserver {


    fun init() {
       bnv.setIconSize(25f, 25f)
       bnv.setTextVisibility(false)
       bnv.enableItemShiftingMode(false)
       bnv.enableShiftingMode(false)
       bnv.enableAnimation(false)
        for (i in 0 until bnv.menu.size()) {
            bnv.setIconTintList(i, null)
        }
        bnv.setOnNavigationItemSelectedListener {
            val nextActivity =
                    when (it.itemId) {
                        R.id.nav_item_nearby -> NearbyActivity::class.java
                        R.id.nav_item_ad -> AdActivity::class.java
                        R.id.nav_item_message -> MessageActivity::class.java
                        R.id.nav_item_menu -> MenuActivity::class.java
                        else -> {
                            System.out.print("Ошибка при выборе меню")
                            null
                        }
                    }
            if (nextActivity != null) {
                val intent = Intent(activity, nextActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                activity.startActivity(intent)
                activity.overridePendingTransition(0,0)
                true
            } else {
                false
            }
        }
        bnv.menu.getItem(navNumber).isChecked = true
    }
}

fun BaseActivity.setupBottomNavigation(uid: String, navNumber: Int) {
    val bnv = BottomNavigation(bottom_navigation_view, navNumber, this)
    this.lifecycle.addObserver(bnv)
}