package com.fanhl.ppt.dummy.io.rest.service

import com.fanhl.ppt.io.rest.model.Forms
import com.fanhl.ppt.model.Room
import io.reactivex.Observable

/**
 * 广场，大厅，旋转游戏列表，聊天对话等
 */
class SquareService {
    fun getGameList(
            page: Int = 1,
            page_size: Int? = null
    ) = Observable.create<Forms<Room>> {
        try {
            Thread.sleep(500)

            val list = (1..10).map { Room(it.toLong()) }
            it.onNext(Forms(list, "pre", "next"))
            it.onComplete()
        } catch(e: Exception) {
            it.onError(e)
        }
    }!!

    fun room(id: Long) = Observable.create<Room> {
        try {
            Thread.sleep(500)

            val room = Room(id, 233)

            it.onNext(room)
            it.onComplete()
        } catch(e: Exception) {
            it.onError(e)
        }
    }!!
}

