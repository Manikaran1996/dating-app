package com.mk.datingappdemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int spaceRight, spaceBottom, spaceLeft;

    public SpacesItemDecoration(int spaces) {
        this.spaceBottom = this.spaceRight = spaces;
    }

    public SpacesItemDecoration(int spaceRight, int spaceBottom) {
        this.spaceLeft = this.spaceRight = spaceRight;
        this.spaceBottom = spaceBottom;
    }

    public SpacesItemDecoration(int spaceLeft, int spaceRight, int spaceBottom) {
        this.spaceLeft = spaceLeft;
        this.spaceRight = spaceRight;
        this.spaceBottom = spaceBottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = spaceRight;
        outRect.bottom = spaceBottom;
        outRect.left = spaceLeft;
    }
}
