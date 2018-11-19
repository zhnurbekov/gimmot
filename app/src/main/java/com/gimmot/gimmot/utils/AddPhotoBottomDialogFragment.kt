package com.gimmot.gimmot.utils

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gimmot.gimmot.R
import kotlinx.android.synthetic.main.dialog_search_setup.*
import kotlinx.android.synthetic.main.dialog_search_setup.view.*
import com.jaygoo.widget.RangeSeekBar
import com.jaygoo.widget.OnRangeChangedListener




class AddPhotoBottomDialogFragment : BottomSheetDialogFragment() {

    fun newInstance(): AddPhotoBottomDialogFragment {
        return AddPhotoBottomDialogFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater!!.inflate(R.layout.dialog_search_setup, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mAgeCount =  view.age_count
        view.age_seekBar.setIndicatorTextDecimalFormat("0");
        view.age_seekBar.setValue(20f, 70f)
        view.age_seekBar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(view: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                mAgeCount.setText( Math.round(leftValue).toString()  + " - " +  Math.round(rightValue).toString())

            }

            override fun onStartTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //start tracking touch
            }

            override fun onStopTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //stop tracking touch
            }
        })



    }
}