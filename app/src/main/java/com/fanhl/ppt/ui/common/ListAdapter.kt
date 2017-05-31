package com.fanhl.ppt.ui.common

import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.*

/**
 * Created by fanhl on 16/5/17.
 */
abstract class ListAdapter<VH : ListAdapter.ViewHolder<ITEM>, ITEM : Any> : RecyclerView.Adapter<VH>(), Listable<ITEM> {
    private var itemClickListener: ((position: Int, holder: ViewHolder<ITEM>) -> Unit)? = null
    private var itemLongClickListener: ((position: Int, holder: ViewHolder<ITEM>) -> Boolean)? = null

    override val list by lazy { ArrayList<ITEM>() }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener { itemClickListener?.invoke(position, holder) }
        holder.itemView.setOnLongClickListener { itemLongClickListener != null && itemLongClickListener!!(position, holder) }

        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setOnItemClickListener(listener: ((position: Int, holder: ViewHolder<ITEM>) -> Unit)?) {
        this.itemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: ((position: Int, holder: ViewHolder<ITEM>) -> Boolean)?) {
        this.itemLongClickListener = listener
    }

    // -------------- listable ---------------

    override fun addItem(item: ITEM) {
        val position = list.size
        list += item
        notifyItemInserted(position)
    }

    override fun addItems(items: List<ITEM>) {
        val positionStart = list.size
        list += items
        notifyItemRangeInserted(positionStart, items.size)
    }

    override fun clearItems() {
        val itemCount = list.size
        list.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    override fun replaceItems(items: List<ITEM>) {
        val oldSize = list.size
        list.clear()
        list += items
        val newSize = list.size

        if (newSize == oldSize) {
            notifyItemRangeChanged(0, oldSize)
        } else if (newSize > oldSize) {
            notifyItemRangeChanged(0, oldSize)
            notifyItemRangeInserted(oldSize, newSize - oldSize)
        } else {
            notifyItemRangeChanged(0, newSize)
            notifyItemRangeRemoved(newSize, oldSize - newSize)
        }
    }

    open class ViewHolder<ITEM : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var data: ITEM

        open fun bind(data: ITEM) {
            this.data = data
        }
    }
}