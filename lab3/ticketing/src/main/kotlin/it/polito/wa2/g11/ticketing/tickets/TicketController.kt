package it.polito.wa2.g11.ticketing.tickets

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping("/API/ticket")
    fun createTicket(@RequestBody ticket: Ticket): TicketDTO {
        return ticketService.createTicket(ticket)
    }

    @PutMapping("/API/ticket/{tid}")
    fun updateTicket(@PathVariable tid: Int, @RequestBody ticket: Ticket): TicketDTO {
        return ticketService.updateTicket(ticket)
    }

    @DeleteMapping("/API/ticket/{tid}")
    fun deleteTicket(@PathVariable tid: Int) {
        ticketService.deleteTicket(tid)
    }

    @DeleteMapping("/API/tickets")
    fun deleteAll() {
        ticketService.deleteAll()
    }
}