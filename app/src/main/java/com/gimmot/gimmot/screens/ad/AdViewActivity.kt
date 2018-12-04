package com.gimmot.gimmot.screens.ad

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gimmot.gimmot.R
import kotlinx.android.synthetic.main.activity_add_view.*



class AdViewActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view)

        back_btn.setOnClickListener {
          finish()
        }
    }
}