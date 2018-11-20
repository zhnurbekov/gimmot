package com.gimmot.gimmot.utils

import android.annotation.SuppressLint
import android.app.Dialog
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
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.annotation.NonNull
import android.support.annotation.Nullable


class AddPhotoBottomDialogFragment : BottomSheetDialogFragment() {

    fun newInstance(): AddPhotoBottomDialogFragment {
        return AddPhotoBottomDialogFragment()
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }

        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {

        }
    }

    @SuppressLint("RestrictedApi", "ResourceAsColor")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.dialog_search_setup, null)
        dialog.setContentView(contentView)
        val layoutParams = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
            behavior.peekHeight = 1800
        }
        contentView.online_btn.setOnClickListener{
            contentView.online_btn.setBackgroundResource(R.drawable.button_border)
            contentView.show_all.setBackgroundResource(R.drawable.textview_border)
            contentView.show_all.setTextColor(resources.getColor(R.color.hind))
            contentView.online_btn.setTextColor(resources.getColor(R.color.black))
        }
        contentView.show_all.setOnClickListener{
            contentView.show_all.setBackgroundResource(R.drawable.button_border)
            contentView.show_all.setTextColor(resources.getColor(R.color.black))
            contentView.online_btn.setBackgroundResource(R.drawable.textview_border)
            contentView.online_btn.setTextColor(resources.getColor(R.color.hind))
        }
        contentView.age_seekBar.setValue(20f, 70f)
        contentView.age_seekBar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(view: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                contentView.age_count.setText(Math.round(leftValue).toString() + " - " + Math.round(rightValue).toString())

            }

            override fun onStartTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //start tracking touch
            }

            override fun onStopTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //stop tracking touch
            }
        })

        contentView.growth_seekBar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(view: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                contentView.growth_count.setText(Math.round(leftValue).toString() + " - " + Math.round(rightValue).toString())

            }

            override fun onStartTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //start tracking touch
            }

            override fun onStopTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //stop tracking touch
            }
        })

        contentView.weight_seekBar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(view: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                contentView.weight_count.setText(Math.round(leftValue).toString() + " - " + Math.round(rightValue).toString())

            }

            override fun onStartTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //start tracking touch
            }

            override fun onStopTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //stop tracking touch
            }
        })

        contentView.size_arg_seekBar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(view: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
                contentView.size_arg_count.setText(Math.round(leftValue).toString() + " - " + Math.round(rightValue).toString())

            }

            override fun onStartTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //start tracking touch
            }

            override fun onStopTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //stop tracking touch
            }
        })
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}