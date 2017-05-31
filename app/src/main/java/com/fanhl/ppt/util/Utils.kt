package com.fanhl.ppt.util

import android.os.Build

object Utils {
    /**
     *  for chromebook?
     */
    fun isChrome() = Build.BRAND == "chromium" || Build.BRAND == "chrome"
}