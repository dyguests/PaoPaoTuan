package com.fanhl.ppt

import android.app.Application
import com.fanhl.ppt.model.User

/**
 * Created by fanhl on 2017/5/31.
 */
class App : Application() {
    var user: User? = null

    override fun onCreate() {
        super.onCreate()

        //test code
        user = User(1)
    }
}

