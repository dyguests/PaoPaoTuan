package com.fanhl.ppt.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.util.Log
import android.view.*
import com.fanhl.ppt.Constant.PAGE_SIZE_DEFAULT
import com.fanhl.ppt.R
import com.fanhl.ppt.model.Room
import com.fanhl.ppt.ui.common.BaseActivity
import com.fanhl.ppt.ui.common.ListAdapter
import com.fanhl.ppt.ui.square.RoomActivity
import com.fanhl.ppt.util.ToastUtil
import com.fanhl.ppt.util.extensions.addOnScrollBottomListener
import com.fanhl.ppt.util.extensions.postRefreshing
import com.fanhl.ppt.util.extensions.subscribeBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.particle_swipe_recycler.*

class MainActivity : BaseActivity() {
    private val adapter by lazy { RoomAdapter() }

    /**
     * 要加载数据时的页码
     */
    private var page = 1
    /**
     * 能否加载更多
     * 注：第一次refresh时为false,加载中时为false,加载完后无新数据时为false;
     * 加载完后还有新数据时为true
     */
    private var isLoadMoreable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignViews()
        initData()
        refreshData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> true
        else                 -> super.onOptionsItemSelected(item)
    }

    private fun assignViews() {
        setSupportActionBar(toolbar)

        add_fab.setOnClickListener { Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }

        swipe_refresh_layout.setOnRefreshListener { refreshData() }
        recycler_view.addOnScrollBottomListener { loadData(true) }
        recycler_view.itemAnimator = DefaultItemAnimator()
    }

    private fun initData() {
        recycler_view.adapter = adapter

        adapter.setOnItemClickListener { position, holder -> RoomActivity.launch(this@MainActivity, holder.data.id) }
    }

    private fun refreshData() {
        swipe_refresh_layout.postRefreshing = true
        page = 1
        isLoadMoreable = true

        loadData(false)
    }

    private fun loadData(isLoadMore: Boolean) {
        if (!isLoadMoreable) return
        isLoadMoreable = false

        app.client.squareService
                .getGameList(
                        page = page,
                        page_size = PAGE_SIZE_DEFAULT
                )
                .filter { it.results != null }
                .map {
                    if (it.next != null) {
                        isLoadMoreable = true
                        page++
                    } else {
                        isLoadMoreable = false
                    }
                    it.results!!
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            if (isLoadMore) {
                                adapter.addItems(it)
                            } else {
                                adapter.replaceItems(it)
                            }
                        },
                        onError = {
                            swipe_refresh_layout.postRefreshing = false
                            ToastUtil.showLongToast(this@MainActivity, "加载游戏列表失败")
                        },
                        onComplete = {
                            swipe_refresh_layout.postRefreshing = false
                            Log.d(TAG, "加载拆卸单数据完成。")
                        }
                )
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        fun launch(context: Context) = context.startActivity(Intent(context, MainActivity::class.java))
    }
}

class RoomAdapter : ListAdapter<RoomAdapter.ViewHolder, Room>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_room, parent, false))

    inner class ViewHolder(view: View) : ListAdapter.ViewHolder<Room>(view)
}
