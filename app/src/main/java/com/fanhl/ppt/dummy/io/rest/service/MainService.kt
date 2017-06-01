package com.fanhl.ppt.dummy.io.rest.service

import com.fanhl.ppt.io.rest.model.Forms
import com.fanhl.ppt.model.Game
import io.reactivex.Observable

class MainService {
    fun getGameList(
            page: Int = 1,
            page_size: Int? = null
    ) = Observable.create<Forms<Game>> {
        try {
            val list = (1..10).map { Game(it.toLong()) }
            it.onNext(Forms(list, "pre", "next"))
            it.onComplete()
        } catch(e: Exception) {
            it.onError(e)
        }
    }!!

}

