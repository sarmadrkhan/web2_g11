package it.polito.wa2.g11.ticketing.tickets

import org.springframework.web.bind.annotation.*

@RestController
class TicketController(
    private val ticketService: TicketServiceImpl
) {
    @GetMapping("/tickets")
    fun getAll(): List<TicketDTO> {
        return ticketService.getAll()
    }

    @GetMapping("/ticket/{tid}")
    fun getTicket(@PathVariable tid: Int): TicketDTO? {
        return ticketService.getTicket(tid)
    }

    @PostMapping("/ticket")
    fun createTicket(@RequestBody ticket: Ticket): TicketDTO {
        return ticketService.createTicket(ticket)
    }

    @PutMapping("/ticket/{tid}")
    fun updateTicket(@PathVariable tid: Int, @RequestBody ticket: Ticket): TicketDTO {
        return ticketService.updateTicket(ticket)
    }

    @DeleteMapping("/ticket/{tid}")
    fun deleteTicket(@PathVariable tid: Int) {
        ticketService.deleteTicket(tid)
    }

    @DeleteMapping("/tickets")
    fun deleteAll() {
        ticketService.deleteAll()
    }
}