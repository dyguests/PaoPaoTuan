package com.fanhl.ppt.ui.game

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import com.fanhl.ppt.R
import com.fanhl.ppt.ui.common.BaseActivity
import com.fanhl.ppt.util.Utils

class GameActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && !Utils.isChrome()) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

//        if (Build.VERSION.SDK_INT >= 21) {
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
//        }

        setContentView(R.layout.activity_game)
    }

    companion object {
        fun launch(context: Context) = context.startActivity(Intent(context, GameActivity::class.java))
    }
}
