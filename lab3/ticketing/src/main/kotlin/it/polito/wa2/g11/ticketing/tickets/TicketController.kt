package it.polito.wa2.g11.ticketing.tickets

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TicketController(
    private val ticketService: TicketServiceImpl
) {
    @GetMapping("/API/tickets")
    fun getAll(): List<TicketDTO> {
        return ticketService.getAll()
    }

    @GetMapping("/API/ticket/{tid}")
    fun getTicket(@PathVariable tid: Int): TicketDTO? {
        return ticketService.getTicket(tid)
    }
}