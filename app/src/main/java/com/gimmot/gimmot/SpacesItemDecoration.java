package com.gimmot.gimmot;


import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private Context mContext;

    public SpacesItemDecoration(int space,Context context) {
        this.space = space;
        this.mContext = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildLayoutPosition(view) == 1) {
            outRect.top = mContext.getResources().getDimensionPixelSize(R.dimen.font_90dp) - space;
        } else {
            outRect.top = space;
        }
        outRect.bottom = space;
    }
}