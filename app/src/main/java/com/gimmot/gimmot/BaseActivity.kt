package com.gimmot.gimmot

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gimmot.gimmot.screens.ad.AdActivity
import com.gimmot.gimmot.screens.common.BaseViewModel
import com.gimmot.gimmot.screens.common.CommonViewModel
import com.gimmot.gimmot.screens.common.ViewModelFactory
import com.gimmot.gimmot.screens.login.LoginActivity
import com.gimmot.gimmot.screens.menu.MenuActivity
import com.gimmot.gimmot.screens.nearby.NearbyActivity
import com.gimmot.gimmot.utils.showToast
import kotlinx.android.synthetic.main.bottom_navigation_view.*


abstract class BaseActivity : AppCompatActivity() {
    private val TAG = BaseActivity::class.java!!.getSimpleName()
    lateinit var commonViewModel: CommonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        commonViewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)
        commonViewModel.errorMessage.observe(this, Observer {
            it?.let {
                this.showToast(it)
            }
        })
    }

    inline fun <reified T : BaseViewModel> initViewModel(): T =
            ViewModelProviders.of(this, ViewModelFactory(
                    application as App,
                    commonViewModel,
                    commonViewModel)).get(T::class.java)

    fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    companion object {
        const val TAG = "BaseActivity"
    }


/*
    fun setupBottomNavigation() {
        bottom_navigation_view.setIconSize(25f, 25f)
        bottom_navigation_view.setTextVisibility(false)
        bottom_navigation_view.enableItemShiftingMode(false)
        bottom_navigation_view.enableShiftingMode(false)
        bottom_navigation_view.enableAnimation(false)
        for (i in 0 until bottom_navigation_view.menu.size()) {
            bottom_navigation_view.setIconTintList(i, null)
        }
        bottom_navigation_view.setOnNavigationItemSelectedListener {
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
                val intent = Intent(this, nextActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0,0)
                true
            } else {
                false
            }
        }
        bottom_navigation_view.menu.getItem(navNumber).isChecked = true
    }*/

}