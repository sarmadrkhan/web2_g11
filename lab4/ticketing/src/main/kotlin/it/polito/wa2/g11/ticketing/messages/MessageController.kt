package it.polito.wa2.g11.ticketing.messages

import org.springframework.web.bind.annotation.*

@RestController
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping("/messages")
    fun getAll(): List<MessageDTO> {
        return messageService.getAll()
    }

    @GetMapping("/message/{mid}")
    fun getMessage(@PathVariable mid: Int): MessageDTO? {
        return messageService.getMessage(mid)
    }

    @GetMapping("/messages/sender/{sid}")
    fun getSenderMessages(@PathVariable sid: String): List<MessageDTO> {
        return messageService.getSenderMessages(sid)
    }

    @GetMapping("/messages/chat/{cid}")
    fun getChatMessages(@PathVariable cid: Int): List<MessageDTO> {
        return messageService.getChatMessages(cid)
    }

    @PostMapping("/messages")
    fun createMessage(@RequestBody message: Message): MessageDTO {
        return messageService.createMessage(message)
    }

    @PutMapping("/message")
    fun updateMessage(@RequestBody message: Message): MessageDTO {
        return messageService.updateMessage(message)
    }

    @DeleteMapping("/message/{mid}")
    fun deleteMessage(@PathVariable mid: Int) {
        messageService.deleteMessage(mid)
    }

    @DeleteMapping("/messages/chat/{cid}")
    fun deleteChatMessages(@PathVariable cid: Int) {
        messageService.deleteChatMessages(cid)
    }

    @DeleteMapping("/messages")
    fun deleteAll() {
        messageService.deleteAll()
    }
}