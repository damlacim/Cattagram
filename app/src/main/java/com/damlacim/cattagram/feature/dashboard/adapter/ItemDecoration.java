package com.damlacim.cattagram.feature.dashboard.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Damla Cim on 24.04.2023
 */

public class ItemDecoration extends RecyclerView.ItemDecoration {

    private final int horizontalSpace;
    private final int verticalSpace;

    public ItemDecoration(Context context) {
        horizontalSpace = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, context.getResources().getDisplayMetrics());
        verticalSpace = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = horizontalSpace;
        outRect.right = horizontalSpace;
        outRect.top = verticalSpace;
        outRect.bottom = verticalSpace;
    }
}