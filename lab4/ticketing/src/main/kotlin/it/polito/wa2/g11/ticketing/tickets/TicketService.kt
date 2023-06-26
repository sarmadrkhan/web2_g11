package it.polito.wa2.g11.ticketing.tickets

interface TicketService {
    fun getAll(): List<TicketDTO>

    fun getTicket(id: Int): TicketDTO?

    fun createTicket(ticket: Ticket): TicketDTO

    fun updateTicket(ticket: Ticket): TicketDTO

    fun deleteTicket(id: Int)

    fun deleteAll()
}