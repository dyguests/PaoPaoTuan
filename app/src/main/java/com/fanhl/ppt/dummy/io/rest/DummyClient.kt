package com.fanhl.ppt.dummy.io.rest

import com.fanhl.ppt.dummy.io.rest.service.SquareService

class DummyClient {
    val squareService by lazy { SquareService() }
}