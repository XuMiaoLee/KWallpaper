package com.github.xumiaolee.kwallpaper.view

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * @author xuyj
 * @date 2018/4/20 on 14:25
 * @description includeEdge:边距是否需要间距
 */

class GridSpacingItemDecoration(val spanCount: Int, val spacing: Int, val includeEdge: Boolean) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent?.getChildAdapterPosition(view) // item position
        val column = position?.rem(spanCount)  // item column

        if (includeEdge) {
            outRect?.left = spacing - column?.times(spacing)?.div(spanCount)!! // spacing - column * ((1f / spanCount) * spacing)
            outRect?.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect?.top = spacing
            }
            outRect?.bottom = spacing // item bottom
        } else {
            outRect?.left = column?.times(spacing)?.div(spanCount)!! // column * ((1f / spanCount) * spacing)
            outRect?.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect?.top = spacing // item top
            }
        }
    }
}