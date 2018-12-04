package com.gimmot.gimmot.screens.menu

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import com.gimmot.gimmot.R
import kotlinx.android.synthetic.main.dialog_search_setup.view.*
import com.jaygoo.widget.RangeSeekBar
import com.jaygoo.widget.OnRangeChangedListener
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.annotation.Nullable
import com.gimmot.gimmot.screens.ad.AdViewActivity


class SetupBottomDialogFragment : BottomSheetDialogFragment() {

    fun newInstance(): SetupBottomDialogFragment {
        return SetupBottomDialogFragment()
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
        val contentView = View.inflate(context, R.layout.dialog_setup_fragment, null)
        dialog.setContentView(contentView)
        val layoutParams = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
            //behavior.peekHeight = 1800
        }

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}