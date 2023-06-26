package it.polito.wa2.g11.ticketing.messages

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping("/API/messages")
    fun getAll(): List<MessageDTO> {
        return messageService.getAll()
    }

    @GetMapping("/API/message/{mid}")
    fun getMessage(@PathVariable mid: Int): MessageDTO? {
        return messageService.getMessage(mid)
    }

    @GetMapping("/API/messages/sender/{sid}")
    fun getSenderMessages(@PathVariable sid: String): List<MessageDTO> {
        return messageService.getSenderMessages(sid)
    }

    @GetMapping("/API/messages/chat/{cid}")
    fun getChatMessages(@PathVariable cid: Int): List<MessageDTO> {
        return messageService.getChatMessages(cid)
    }

    @PostMapping("/API/messages")
    fun createMessage(@RequestBody message: Message): MessageDTO {
        return messageService.createMessage(message)
    }

    @PutMapping("/API/message")
    fun updateMessage(@RequestBody message: Message): MessageDTO {
        return messageService.updateMessage(message)
    }

    @DeleteMapping("/API/message/{mid}")
    fun deleteMessage(@PathVariable mid: Int) {
        messageService.deleteMessage(mid)
    }

    @DeleteMapping("/API/messages/chat/{cid}")
    fun deleteChatMessages(@PathVariable cid: Int) {
        messageService.deleteChatMessages(cid)
    }

    @DeleteMapping("/API/messages")
    fun deleteAll() {
        messageService.deleteAll()
    }
}