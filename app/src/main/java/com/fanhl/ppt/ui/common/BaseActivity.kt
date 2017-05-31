package com.fanhl.ppt.ui.common

import android.support.v7.app.AppCompatActivity
import com.fanhl.ppt.App

open class BaseActivity : AppCompatActivity() {
    val app get() = application as App
}