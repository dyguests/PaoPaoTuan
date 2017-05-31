package com.fanhl.ppt.ui.common

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * @author Jack Tony
 * *
 * @brief recyle view 滚动监听器
 * *
 * @date 2015/4/6
 */
abstract class OnRcvScrollListener : RecyclerView.OnScrollListener(), OnBottomListener {

    private val TAG = javaClass.simpleName

    enum class LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    /**
     * layoutManager的类型（枚举）
     */
    protected var layoutManagerType: LAYOUT_MANAGER_TYPE? = null

    /**
     * 最后一个的位置
     */
    private var lastPositions: IntArray? = null

    /**
     * 最后一个可见的item的位置
     */
    private var lastVisibleItemPosition: Int = 0

    /**
     * 当前滑动的状态
     */
    private var currentScrollState = 0

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView!!.layoutManager
        //  int lastVisibleItemPosition = -1;
        if (layoutManagerType == null) {
            if (layoutManager is LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR
            } else if (layoutManager is GridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.GRID
            } else if (layoutManager is StaggeredGridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID
            } else {
                throw RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager")
            }
        }

        when (layoutManagerType) {
            LAYOUT_MANAGER_TYPE.LINEAR         -> lastVisibleItemPosition = (layoutManager as LinearLayoutManager)
                    .findLastVisibleItemPosition()
            LAYOUT_MANAGER_TYPE.GRID           -> lastVisibleItemPosition = (layoutManager as GridLayoutManager)
                    .findLastVisibleItemPosition()
            LAYOUT_MANAGER_TYPE.STAGGERED_GRID -> {
                val staggeredGridLayoutManager = layoutManager as StaggeredGridLayoutManager
                if (lastPositions == null) {
                    lastPositions = IntArray(staggeredGridLayoutManager.spanCount)
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions)
                lastVisibleItemPosition = findMax(lastPositions)
            }
        }

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        currentScrollState = newState
        val layoutManager = recyclerView!!.layoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        if (visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE &&
                lastVisibleItemPosition >= totalItemCount - 1) {
            onScrollBottom()
        }
    }

    private fun findMax(lastPositions: IntArray?): Int {
        var max = lastPositions!![0]
        for (value in lastPositions) {
            if (value > max) {
                max = value
            }
        }
        return max
    }
}