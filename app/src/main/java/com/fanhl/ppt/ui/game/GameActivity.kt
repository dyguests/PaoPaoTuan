package com.fanhl.ppt.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanhl.ppt.App
import com.fanhl.ppt.R
import com.fanhl.ppt.dummy.ChatDummy
import com.fanhl.ppt.model.Chat
import com.fanhl.ppt.ui.common.FullscreenActivity
import com.fanhl.ppt.ui.common.ListAdapter
import com.fanhl.ppt.util.ToastUtils
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.particle_chat_view.*

class GameActivity : FullscreenActivity() {
    val chatViewHolder by lazy { ChatViewHolder() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        assignViews()
        chatViewHolder
    }

    private fun assignViews() {
        chat_fab.setOnClickListener { callChatBox() }
        dice_fab.setOnClickListener { ToastUtils.showShortToast(this@GameActivity, "dice") }
    }

    /**
     * 打开输入框
     */
    private fun callChatBox() {
        ToastUtils.showShortToast(this@GameActivity, "chat")
    }

    inner class ChatViewHolder {
        val adapter by lazy { ChatAdapter(app) }

        init {
            recycler_view.adapter = adapter
            adapter.replaceItems(ChatDummy.list())
        }
    }

    companion object {
        fun launch(context: Context) = context.startActivity(Intent(context, GameActivity::class.java))
    }
}

class ChatAdapter(
        val app: App
) : ListAdapter<ChatAdapter.ViewHolder, Chat>() {

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return when (item.type) {
            Chat.TYPE_CHAT -> if (item.userId == app.user?.id) Chat.TYPE_CHAT_ME else Chat.TYPE_CHAT_OTHER
            Chat.TYPE_DICE -> if (item.userId == app.user?.id) Chat.TYPE_DICE_ME else Chat.TYPE_DICE_OTHER
            else           -> Chat.TYPE_TIME
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = when (viewType) {
        Chat.TYPE_CHAT_ME    -> ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_chat_me, parent, false))
        Chat.TYPE_CHAT_OTHER -> ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_chat_other, parent, false))
        Chat.TYPE_DICE_ME    -> ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_chat_time, parent, false))
        Chat.TYPE_DICE_OTHER -> ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_chat_time, parent, false))
        else                 -> ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_chat_time, parent, false))
    }

    inner class ViewHolder(view: View) : ListAdapter.ViewHolder<Chat>(view)
}
