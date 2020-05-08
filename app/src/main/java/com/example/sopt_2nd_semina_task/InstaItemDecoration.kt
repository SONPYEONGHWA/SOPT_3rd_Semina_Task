package com.example.sopt_2nd_semina_task

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class InstaItemDecoration( private val divHeight:Int) : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = divHeight
    }


}