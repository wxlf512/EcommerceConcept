package dev.wxlf.feature.explorer.presentation.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CategoriesItemDecoration(
    private val leftMargin: Int,
    private val rightMargin: Int,
    private val dividerSize: Int
) : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            when {
                parent.getChildAdapterPosition(view) == 0 -> {
                    left = leftMargin
                }
                parent.getChildAdapterPosition(view) == (parent.adapter?.itemCount?.minus(1)
                    ?: 0) -> {
                    left = dividerSize
                    right = rightMargin
                }
                else -> {
                    left = dividerSize
                }
            }
        }
    }
}