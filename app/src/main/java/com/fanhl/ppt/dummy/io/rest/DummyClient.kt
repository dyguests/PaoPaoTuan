package com.fanhl.ppt.dummy.io.rest

import com.fanhl.ppt.dummy.io.rest.service.MainService

class DummyClient {
    val mainService by lazy { MainService() }
}