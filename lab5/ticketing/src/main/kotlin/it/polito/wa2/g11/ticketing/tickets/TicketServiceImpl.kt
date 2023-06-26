package it.polito.wa2.g11.ticketing.tickets

import io.micrometer.observation.annotation.Observed
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository
) : TicketService {
    @Observed
    override fun getAll(): List<TicketDTO> {
        return ticketRepository.findAll().map { it.toDTO() }
    }

    @Observed
    override fun getTicket(id: Int): TicketDTO? {
        return ticketRepository
            .findByIdOrNull(id)
            ?.toDTO()
    }

    @Observed
    override fun createTicket(ticket: Ticket): TicketDTO {
        return ticketRepository.save(ticket).toDTO()
    }

    @Observed
    override fun updateTicket(ticket: Ticket): TicketDTO {
        return ticketRepository.save(ticket).toDTO()
    }

    @Observed
    override fun deleteTicket(id: Int) {
        ticketRepository.deleteById(id)
    }

    @Observed
    override fun deleteAll() {
        ticketRepository.deleteAll()
    }
}