package it.polito.wa2.g11.ticketing.tickets

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tickets")
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var tid: Int = 0

    var uid: String = ""
    var ean: String = ""
    var eid: String = ""
    var title: String = " "
    var idChat: Int = 0
    var description: String = " "

    @Column(name = "date_open", columnDefinition = "TIMESTAMP")
    var openDate: LocalDateTime = LocalDateTime.now()

    @Column(name = "date_last_update", columnDefinition = "TIMESTAMP")
    var lastUpdateDate: LocalDateTime = LocalDateTime.now()
    var priority: Int = 0
    var status: String = " "
}