package com.fanhl.ppt.io.rest.model

/**
 * 安装单
 *
 * count : 19
 * next : http://192.168.1.204:8001/process/install/?page=2
 * previous : “”
 * results : [{}]
 * Created by xw on 2016/5/3.
 */
data class Forms<T>(
        var results: List<T>? = null,
        var previous: String? = null,
        var next: String? = null,
        var count: Int = 0
)