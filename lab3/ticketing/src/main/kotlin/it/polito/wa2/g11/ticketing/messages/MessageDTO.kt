package it.polito.wa2.g11.ticketing.messages

import java.time.LocalDateTime

data class MessageDTO(
    val mid: Int,
    val idChat: Int,
    val idSender: String,
    val timeStamp: LocalDateTime
)

fun Message.toDTO(): MessageDTO {
    return MessageDTO(
        mid,
        idChat,
        idSender,
        timestamp
    )
}