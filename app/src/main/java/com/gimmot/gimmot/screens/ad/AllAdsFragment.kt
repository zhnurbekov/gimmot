package com.gimmot.gimmot.screens.ad

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gimmot.gimmot.R
import kotlinx.android.synthetic.main.fragment_all_ads.*


class AllAdsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_all_ads, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ad_open_layout.setOnClickListener {
            val intent = Intent(activity, AdViewActivity::class.java)
            startActivity(intent)
        }
    }

}
