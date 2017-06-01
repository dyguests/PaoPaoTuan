package com.fanhl.ppt.dummy

import com.fanhl.ppt.model.Chat

object ChatDummy {
    fun list(): List<Chat> {
        val list = (1..20).map { Chat(it.toLong(), 1, Chat.TYPE_CHAT) }
        return list
    }

}