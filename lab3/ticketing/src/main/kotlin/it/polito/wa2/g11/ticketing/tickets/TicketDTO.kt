package it.polito.wa2.g11.ticketing.tickets

import java.time.LocalDateTime

data class TicketDTO(
    val tid: Int,
    val uid: String,
    val ean: String,
    val eid: String,
    val title: String,
    val chatId: Int,
    val description: String,
    val openDate: LocalDateTime,
    val lastUpdateDate: LocalDateTime,
    val priority: Int,
    val status: String
)

fun Ticket.toDTO(): TicketDTO {
    return TicketDTO(
        tid,
        uid,
        ean,
        eid,
        title,
        idChat,
        description,
        openDate,
        lastUpdateDate,
        priority,
        status
    )
}
