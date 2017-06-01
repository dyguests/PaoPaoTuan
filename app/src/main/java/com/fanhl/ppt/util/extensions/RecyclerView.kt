package com.fanhl.ppt.util.extensions

import android.support.v7.widget.RecyclerView
import com.fanhl.ppt.ui.common.OnRcvScrollListener

/**
 * Created by fanhl on 2017/5/25.
 */

fun RecyclerView.addOnScrollBottomListener(onScrollBottom: () -> Unit): Unit = this.addOnScrollListener(object : OnRcvScrollListener() {
    override fun onScrollBottom() {
        onScrollBottom()
    }
})