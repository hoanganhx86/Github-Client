package com.anhnguyen.githubclient.ui.widget.rcv;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private final int mColumnCount;
    private final int mSpace;

    public SpacesItemDecoration(int space, int columnCount) {
        this.mSpace = space;
        this.mColumnCount = columnCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mSpace;
        outRect.left = mSpace / 2;
        outRect.right = mSpace / 2;
    }
}