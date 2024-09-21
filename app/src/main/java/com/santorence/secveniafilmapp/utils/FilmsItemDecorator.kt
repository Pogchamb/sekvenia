package com.santorence.secveniafilmapp.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilmsItemDecorator(
    private val verticalSpaceHeight: Int,
    private val horizontalSpaceHeight: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
        outRect.left = horizontalSpaceHeight
        outRect.right = horizontalSpaceHeight
    }
}