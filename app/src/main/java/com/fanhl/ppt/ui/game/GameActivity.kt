package com.fanhl.ppt.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fanhl.ppt.R
import com.fanhl.ppt.ui.common.FullscreenActivity
import com.fanhl.ppt.util.ToastUtils
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : FullscreenActivity() {
    val chatViewHolder by lazy { ChatViewHolder() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        assignViews()
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

    companion object {
        fun launch(context: Context) = context.startActivity(Intent(context, GameActivity::class.java))
    }

    inner class ChatViewHolder {
        init {

        }
    }
}
