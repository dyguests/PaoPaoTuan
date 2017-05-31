package com.fanhl.ppt.ui.common

/**
 * Created by fanhl on 16/5/12.
 */
interface Listable<T> {
    val list: List<T>

    fun addItem(item: T)
    fun addItems(items: List<T>)
    fun clearItems()
    fun replaceItems(items: List<T>)
}