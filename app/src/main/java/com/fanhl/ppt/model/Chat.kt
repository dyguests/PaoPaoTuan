package com.fanhl.ppt.model

/**
 * 聊天信息
 */
data class Chat(
        val id: Long,
        /*外键，*/val userId: Long,
        val type: Int
) {
    companion object {
        const val TYPE_CHAT = 1
        const val TYPE_DICE = 2
        const val TYPE_TIME = 0

        const val TYPE_CHAT_ME = 3
        const val TYPE_CHAT_OTHER = 4
        const val TYPE_DICE_ME = 5
        const val TYPE_DICE_OTHER = 6
    }
}