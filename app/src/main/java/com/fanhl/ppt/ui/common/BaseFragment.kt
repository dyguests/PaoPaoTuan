package com.fanhl.ppt.ui.common

import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {
    val baseActivity get() = activity as BaseActivity
    val app get() = baseActivity.app
}