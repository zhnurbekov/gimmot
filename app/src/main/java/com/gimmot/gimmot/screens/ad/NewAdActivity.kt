package com.gimmot.gimmot.screens.ad

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gimmot.gimmot.R
import kotlinx.android.synthetic.main.activity_new_ad.*
import kotlinx.android.synthetic.main.fragment_all_ads.*


class NewAdActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ad)

        back_btn.setOnClickListener {
            val intent = Intent(this, AdActivity::class.java)
            startActivity(intent)
        }
    }
}