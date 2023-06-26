package it.polito.wa2.g11.ticketing.messages

import io.micrometer.observation.annotation.Observed
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@RestController
@Slf4j
@Observed
class MessageController(
    private val messageService: MessageService
) {

    private val log = org.slf4j.LoggerFactory.getLogger(MessageController::class.java)

    @GetMapping("/messages")
    fun getAll(): List<MessageDTO> {
        log.info("Get all messages")
        return messageService.getAll()
    }

    @GetMapping("/message/{mid}")
    fun getMessage(@PathVariable mid: Int): MessageDTO? {
        log.info("Get message $mid")
        return messageService.getMessage(mid)
    }

    @GetMapping("/messages/sender/{sid}")
    fun getSenderMessages(@PathVariable sid: String): List<MessageDTO> {
        log.info("Get messages sent by $sid")
        return messageService.getSenderMessages(sid)
    }

    @GetMapping("/messages/chat/{cid}")
    fun getChatMessages(@PathVariable cid: Int): List<MessageDTO> {
        log.info("Get messages of chat $cid")
        return messageService.getChatMessages(cid)
    }

    @PostMapping("/messages")
    fun createMessage(@RequestBody message: Message): MessageDTO {
        log.info("Create message")
        return messageService.createMessage(message)
    }

    @PutMapping("/message")
    fun updateMessage(@RequestBody message: Message): MessageDTO {
        log.info("Update message ${message.mid}")
        return messageService.updateMessage(message)
    }

    @DeleteMapping("/message/{mid}")
    fun deleteMessage(@PathVariable mid: Int) {
        log.info("Delete message $mid")
        messageService.deleteMessage(mid)
    }

    @DeleteMapping("/messages/chat/{cid}")
    fun deleteChatMessages(@PathVariable cid: Int) {
        log.info("Delete messages of chat $cid")
        messageService.deleteChatMessages(cid)
    }

    @DeleteMapping("/messages")
    fun deleteAll() {
        log.info("Delete all messages")
        messageService.deleteAll()
    }
}