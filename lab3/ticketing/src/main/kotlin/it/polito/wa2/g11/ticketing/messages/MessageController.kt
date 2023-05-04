package it.polito.wa2.g11.ticketing.messages

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping("/API/messages")
    fun getAll(): List<MessageDTO> {
        return messageService.getAll()
    }

    @GetMapping("/API/messages/{mid}")
    fun getMessage(@PathVariable mid: Int): MessageDTO? {
        return messageService.getMessage(mid)
    }

    @GetMapping("/API/messages/chat/{cid}")
    fun getChatMessages(@PathVariable cid: Int): List<MessageDTO> {
        return messageService.getChatMessages(cid)
    }
}