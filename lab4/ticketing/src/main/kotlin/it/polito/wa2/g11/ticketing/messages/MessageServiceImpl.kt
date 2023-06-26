package it.polito.wa2.g11.ticketing.messages

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class MessageServiceImpl(private val messageRepository: MessageRepository) : MessageService {
    override fun getAll(): List<MessageDTO> {
        return messageRepository.findAll().map { it.toDTO() }
    }

    override fun getMessage(id: Int): MessageDTO? {
        return messageRepository
            .findByIdOrNull(id)
            ?.toDTO()
    }

    override fun getSenderMessages(senderId: String): List<MessageDTO> {
        return messageRepository.findByIdSender(senderId).sortedByDescending { it.timestamp }.map { it.toDTO() }
    }

    override fun getChatMessages(chatId: Int): List<MessageDTO> {
        return messageRepository.findByIdChat(chatId).sortedByDescending { it.timestamp }.map { it.toDTO() }
    }

    override fun createMessage(message: Message): MessageDTO {
        return messageRepository.save(message).toDTO()
    }

    override fun updateMessage(message: Message): MessageDTO {
        return messageRepository.save(message).toDTO()
    }

    override fun deleteMessage(id: Int) {
        messageRepository.deleteById(id)
    }

    override fun deleteChatMessages(chatId: Int) {
        messageRepository.deleteByIdChat(chatId)
    }

    override fun deleteAll() {
        messageRepository.deleteAll()
    }
}