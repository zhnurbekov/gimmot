package com.gimmot.gimmot

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import de.hdodenhof.circleimageview.CircleImageView

class SquareImageView(context: Context, attrs: AttributeSet) : CircleImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}