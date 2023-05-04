package it.polito.wa2.g11.ticketing.tickets

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository
) : TicketService {
    override fun getAll(): List<TicketDTO> {
        return ticketRepository.findAll().map { it.toDTO() }
    }

    override fun getTicket(id: Int): TicketDTO? {
        return ticketRepository
            .findByIdOrNull(id)
            ?.toDTO()
    }

    override fun createTicket(ticket: Ticket): TicketDTO {
        return ticketRepository.save(ticket).toDTO()
    }

    override fun updateTicket(ticket: Ticket): TicketDTO {
        return ticketRepository.save(ticket).toDTO()
    }

    override fun deleteTicket(id: Int) {
        ticketRepository.deleteById(id)
    }

    override fun deleteAll() {
        ticketRepository.deleteAll()
    }
}