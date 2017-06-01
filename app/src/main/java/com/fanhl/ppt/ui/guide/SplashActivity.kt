package com.fanhl.ppt.ui.guide

import android.os.Bundle
import android.os.Handler
import com.fanhl.ppt.R
import com.fanhl.ppt.ui.common.BaseActivity
import com.fanhl.ppt.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            MainActivity.launch(this@SplashActivity)
            finish()
        }, 1000)
    }
}

