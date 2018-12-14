package com.gimmot.gimmot.profil

import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.view.WindowManager
import com.gimmot.gimmot.BaseActivity
import com.gimmot.gimmot.R
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.activity_profil.*
import java.util.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat


class ProfilActivity : BaseActivity() {

    private var imageModelArrayList: ArrayList<ImageModel>? = null

    private val myImageList = intArrayOf(R.mipmap.logo, R.mipmap.profil, R.mipmap.ic_add_photo,  R.mipmap.profil,  R.mipmap.profil,  R.mipmap.profil)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        }

        init()

    }

    private fun populateList(): ArrayList<ImageModel> {

        val list = ArrayList<ImageModel>()

        for (i in 0..5) {
            val imageModel = ImageModel()
            imageModel.setImage_drawables(myImageList[i])
            list.add(imageModel)
        }

        return list
    }

    private fun init() {
        pager!!.adapter = SlidingImageAdapter(this, imageModelArrayList!!)

        indicator.setViewPager(pager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.setRadius(4 * density)

        NUM_PAGES = imageModelArrayList!!.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            pager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })

    }

    companion object {

       // private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }
}
