package com.fanhl.ppt.ui.common

import android.support.v7.widget.RecyclerView

/**
 * Created by fanhl on 2016/3/16.
 */
abstract class ClickableAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private var itemClickListener: ((position: Int, holder: RecyclerView.ViewHolder) -> Unit)? = null
    private var itemLongClickListener: ((position: Int, holder: RecyclerView.ViewHolder) -> Boolean)? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener { itemClickListener?.invoke(position, holder) }
        holder.itemView.setOnLongClickListener { itemLongClickListener != null && itemLongClickListener!!(position, holder) }
    }

    fun setOnItemClickListener(listener: ((position: Int, holder: RecyclerView.ViewHolder) -> Unit)?) {
        this.itemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: ((position: Int, holder: RecyclerView.ViewHolder) -> Boolean)?) {
        this.itemLongClickListener = listener
    }
}
