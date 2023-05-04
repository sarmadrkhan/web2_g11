package it.polito.wa2.g11.ticketing.messages

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Int> {
    fun findByIdChat(chatId: Int): List<Message>
    fun deleteByIdChat(chatId: Int)
}