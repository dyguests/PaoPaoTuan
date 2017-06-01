package com.fanhl.ppt.ui.guide

import android.os.Bundle
import android.os.Handler
import com.fanhl.ppt.R
import com.fanhl.ppt.ui.common.BaseActivity
import com.fanhl.ppt.ui.game.GameActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        Handler().post { MainActivity.launch(this@SplashActivity) }
        Handler().postDelayed({
            GameActivity.launch(this@SplashActivity)
            finish()
        }, 1000)
    }
}

