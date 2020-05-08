package com.example.sopt_2nd_semina_task

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class InstaAdapter (private val context : Context) : RecyclerView.Adapter<InstaViewHolder>() {
    var datas = mutableListOf<InstaData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_item_insta,parent,false)
        return InstaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {
        holder.bind(datas[position])
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        holder.itemView.requestLayout()
    }
}