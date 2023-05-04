package it.polito.wa2.g11.ticketing.messages

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "messages")
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var mid: Int = 0

    var idChat: Int = 0
    var idSender: String = ""

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP")
    var timestamp: LocalDateTime = LocalDateTime.now()
}