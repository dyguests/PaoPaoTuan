package com.fanhl.ppt.ui.common

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.fanhl.ppt.App

open class BaseActivity : AppCompatActivity() {
    val app get() = application as App

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else              -> super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }
}