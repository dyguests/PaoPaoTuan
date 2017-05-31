package com.fanhl.ppt.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fanhl.ppt.R
import com.fanhl.ppt.ui.common.FullscreenActivity
import com.fanhl.ppt.util.ToastUtils
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : FullscreenActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        chat_fab.setOnClickListener { ToastUtils.showShortToast(this@GameActivity, "chat") }
        dice_fab.setOnClickListener { ToastUtils.showShortToast(this@GameActivity, "dice") }
    }

    companion object {
        fun launch(context: Context) = context.startActivity(Intent(context, GameActivity::class.java))
    }
}
