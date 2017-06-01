package com.fanhl.ppt.util.extensions

import android.support.v4.widget.SwipeRefreshLayout

/**
 * Created by fanhl on 2017/5/27.
 */

var SwipeRefreshLayout.postRefreshing: Boolean
    get() = this.isRefreshing
    set(value) {
        if (this.isRefreshing != value) this.post { this.isRefreshing = value }
    }
