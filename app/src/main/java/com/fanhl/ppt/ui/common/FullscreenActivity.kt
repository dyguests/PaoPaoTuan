package com.fanhl.ppt.ui.common

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import com.fanhl.ppt.util.Utils

open class FullscreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && !Utils.isChrome()) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

//        if (Build.VERSION.SDK_INT >= 21) {
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
//        }

    }
}