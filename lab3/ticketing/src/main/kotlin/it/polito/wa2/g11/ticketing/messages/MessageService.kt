package it.polito.wa2.g11.ticketing.messages

interface MessageService {
    fun getAll(): List<MessageDTO>

    fun getMessage(id: Int): MessageDTO?

    fun getSenderMessages(senderId: String): List<MessageDTO>

    fun getChatMessages(chatId: Int): List<MessageDTO>

    fun createMessage(message: Message): MessageDTO

    fun updateMessage(message: Message): MessageDTO

    fun deleteMessage(id: Int)

    fun deleteChatMessages(chatId: Int)

    fun deleteAll()
}