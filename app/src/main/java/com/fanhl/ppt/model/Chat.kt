package com.fanhl.ppt.model

/**
 * 聊天信息
 */
data class Chat(
        val id: Long,
        /*外键，*/val userId: Long,
        val type: TYPE
) {
    enum class TYPE {
        CHAT,
        DICE,
        TIME
    }
}