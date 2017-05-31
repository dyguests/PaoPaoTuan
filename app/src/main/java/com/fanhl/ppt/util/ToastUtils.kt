package com.fanhl.ppt.util

import android.content.Context
import android.widget.Toast

/**
 * Created by zmy on 2015/7/13.
 */
object ToastUtils {
    fun showToast(context: Context?, msg: String, duration: Int) {
        var msg = msg
        if (context != null) {
            msg = Utils.nullToDefault(msg)
            Toast.makeText(context.applicationContext, msg, duration).show()
        }
    }

    fun showShortToast(context: Context, msg: String) {
        showToast(context, msg, Toast.LENGTH_SHORT)
    }

    /**
     * add 20160323 fanhl

     * @param context
     * *
     * @param msg
     */
    fun showLongToast(context: Context, msg: String) {
        showToast(context, msg, Toast.LENGTH_LONG)
    }
}
