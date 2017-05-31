package com.fanhl.ppt.util

import android.os.Build
import java.util.*

object Utils {
    /**
     *  for chromebook?
     */
    fun isChrome() = Build.BRAND == "chromium" || Build.BRAND == "chrome"

    fun isNullOrEmpty(list: List<*>?): Boolean {
        if (list == null) {
            return true
        }

        return list.isEmpty()
    }

    fun nullToDefault(s: String?): String {
        var s = s
        if (s == null) {
            s = ""
        }

        return s
    }


    fun <T> nullToDefault(arrayList: ArrayList<T>?): ArrayList<T> {
        if (arrayList == null) {
            return ArrayList()
        }

        return arrayList
    }

    fun getListSize(list: List<*>?): Int {
        if (list == null) {
            return 0
        }

        return list.size
    }
}