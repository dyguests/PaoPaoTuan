package com.fanhl.ppt

import android.app.Application
import com.fanhl.ppt.dummy.io.rest.DummyClient
import com.fanhl.ppt.model.User

/**
 * Created by fanhl on 2017/5/31.
 */
class App : Application() {
    val client by lazy { DummyClient() }

    var user: User? = null

    override fun onCreate() {
        super.onCreate()

        //test code
        user = User(1)
    }
}

