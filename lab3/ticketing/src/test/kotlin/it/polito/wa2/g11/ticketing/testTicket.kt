package it.polito.wa2.g11.ticketing

import it.polito.wa2.g11.ticketing.tickets.Ticket
import it.polito.wa2.g11.ticketing.tickets.TicketDTO
import it.polito.wa2.g11.ticketing.tickets.TicketRepository
import it.polito.wa2.g11.ticketing.tickets.toDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.LocalDateTime

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketControllerTests {
    companion object {
        @Container
        val postgres = PostgreSQLContainer<Nothing>("postgres:latest")
            .apply {
                withDatabaseName("test")
                withUsername("tester")
                withPassword("password")
            }

        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.jpa.hibernate.ddl-auto") { "create-drop" }
        }
    }


    @LocalServerPort
    private val port: Int = 8080

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var ticketRepository: TicketRepository

    @Test
    fun `should get all tickets`() {
        // Given
        val ticket = Ticket()
        ticket.uid = "johndoe@gmail.com"
        ticket.ean = "1234567898765"
        ticket.eid = "janedoe@gmail.com"
        ticket.title = "Title 1"
        ticket.idChat = 1
        ticket.description = "Description 1"
        ticket.openDate = LocalDateTime.of(2022, 5, 4, 10, 0)
        ticket.lastUpdateDate = LocalDateTime.of(2022, 5, 5, 10, 0)
        ticket.priority = 1
        ticket.status = "open"
        println(ticket)
        val ticket2 = Ticket()
        ticket2.uid = "janedoe@gmail.com"
        ticket2.ean = "1234567898766"
        ticket2.eid = "johndoe@gmail.com"
        ticket2.title = "Title 2"
        ticket2.idChat = 2
        ticket2.description = "Description 2"
        ticket2.openDate = LocalDateTime.of(2022, 5, 4, 11, 0)
        ticket2.lastUpdateDate = LocalDateTime.of(2022, 5, 5, 11, 0)
        ticket2.priority = 2
        ticket2.status = "closed"
        println(ticket2)

        ticketRepository.saveAll(listOf(ticket, ticket2))

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/tickets",
            Array<TicketDTO>::class.java
        )

        // Then
        assertThat(response).containsExactlyInAnyOrder(
            ticket.toDTO(),
            ticket2.toDTO()
        )

        ticketRepository.deleteAll(listOf(ticket, ticket2))
    }

    @Test
    fun `should get a ticket by id`() {
        // Given
        val ticket = Ticket()
        ticket.uid = "johndoe@gmail.com"
        ticket.ean = "1234567898765"
        ticket.eid = "janedoe@gmail.com"
        ticket.title = "Title 1"
        ticket.idChat = 1
        ticket.description = "Description 1"
        ticket.openDate = LocalDateTime.of(2022, 5, 4, 10, 0)
        ticket.lastUpdateDate = LocalDateTime.of(2022, 5, 5, 10, 0)
        ticket.priority = 1
        ticket.status = "open"
        ticketRepository.save(ticket)

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/ticket/${ticket.tid}",
            TicketDTO::class.java
        )

        // Then
        assertThat(response).isEqualTo(ticket.toDTO())

        ticketRepository.delete(ticket)
    }
}
