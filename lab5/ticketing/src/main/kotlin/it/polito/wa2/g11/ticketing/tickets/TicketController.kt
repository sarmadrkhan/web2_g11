package it.polito.wa2.g11.ticketing.tickets

import io.micrometer.observation.annotation.Observed
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@RestController
@Slf4j
@Observed
class TicketController(
    private val ticketService: TicketServiceImpl
) {

    private val log = org.slf4j.LoggerFactory.getLogger(TicketController::class.java)

    @GetMapping("/tickets")
    fun getAll(): List<TicketDTO> {
        log.info("Get all tickets")
        return ticketService.getAll()
    }

    @GetMapping("/ticket/{tid}")
    fun getTicket(@PathVariable tid: Int): TicketDTO? {
        log.info("Get ticket $tid")
        return ticketService.getTicket(tid)
    }

    @PostMapping("/ticket")
    fun createTicket(@RequestBody ticket: Ticket): TicketDTO {
        log.info("Create ticket")
        return ticketService.createTicket(ticket)
    }

    @PutMapping("/ticket/{tid}")
    fun updateTicket(@PathVariable tid: Int, @RequestBody ticket: Ticket): TicketDTO {
        log.info("Update ticket $tid")
        return ticketService.updateTicket(ticket)
    }

    @DeleteMapping("/ticket/{tid}")
    fun deleteTicket(@PathVariable tid: Int) {
        log.info("Delete ticket $tid")
        ticketService.deleteTicket(tid)
    }

    @DeleteMapping("/tickets")
    fun deleteAll() {
        log.info("Delete all tickets")
        ticketService.deleteAll()
    }
}