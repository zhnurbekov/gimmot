package com.gimmot.gimmot.adapter;


import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gimmot.gimmot.R;

public class NearbyPersonAdapter extends RecyclerView.ItemDecoration {
    private int space;
    private Context context;

    public NearbyPersonAdapter(int space, Context context) {
        this.context = context;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildLayoutPosition(view) == 1) {
            outRect.top = context.getResources().getDimensionPixelSize(R.dimen.default_margin) - space;
        } else {
            outRect.top = space;
        }
        outRect.bottom = space;
    }
};

