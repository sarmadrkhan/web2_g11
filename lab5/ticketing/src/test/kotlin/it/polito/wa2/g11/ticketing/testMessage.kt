package it.polito.wa2.g11.ticketing

import it.polito.wa2.g11.ticketing.messages.Message
import it.polito.wa2.g11.ticketing.messages.MessageDTO
import it.polito.wa2.g11.ticketing.messages.MessageRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
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
class MessageControllerTests {
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
    private lateinit var messageRepository: MessageRepository

    @Test
    fun `should get all messages`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello World!"
        messageRepository.save(message)

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/messages",
            Array<MessageDTO>::class.java
        )

        // Then
        assertThat(response).isNotNull
        assertThat(response).hasSize(1)
        assertThat(response[0].mid).isEqualTo(message.mid)
        assertThat(response[0].idChat).isEqualTo(message.idChat)
        assertThat(response[0].idSender).isEqualTo(message.idSender)
        assertThat(response[0].timestamp).isEqualTo(message.timestamp)

        messageRepository.delete(message)
    }

    @Test
    fun `should get a message by id`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello World!"

        messageRepository.save(message)

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/message/${message.mid}",
            MessageDTO::class.java
        )

        // Then
        assertThat(response).isNotNull
        assertThat(response.mid).isEqualTo(message.mid)
        assertThat(response.idChat).isEqualTo(message.idChat)
        assertThat(response.idSender).isEqualTo(message.idSender)
        assertThat(response.timestamp).isEqualTo(message.timestamp)

        messageRepository.delete(message)
    }

    @Test
    fun `should not get a message by id`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello World!"

        messageRepository.save(message)

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/message/${message.mid + 1}",
            MessageDTO::class.java
        )

        // Then
        assertThat(response).isNull()

        messageRepository.delete(message)

    }

    @Test
    fun `should get all messages by chat id`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello World!"

        val message2 = Message()
        message2.idChat = 1
        message2.idSender = "janedoe@gmail.com"
        message2.timestamp = LocalDateTime.of(2022, 5, 5, 11, 0)
        message2.content = "Hello World!"

        messageRepository.saveAll(listOf(message, message2))

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/messages/chat/${message.idChat}",
            Array<MessageDTO>::class.java
        )

        // Then
        assertThat(response).isNotNull
        assertThat(response).hasSize(2)
        assertThat(response[0].mid).isEqualTo(message2.mid)
        assertThat(response[0].idChat).isEqualTo(message2.idChat)
        assertThat(response[0].idSender).isEqualTo(message2.idSender)
        assertThat(response[0].timestamp).isEqualTo(message2.timestamp)
        assertThat(response[0].content).isEqualTo(message2.content)
        assertThat(response[1].mid).isEqualTo(message.mid)
        assertThat(response[1].idChat).isEqualTo(message.idChat)
        assertThat(response[1].idSender).isEqualTo(message.idSender)
        assertThat(response[1].timestamp).isEqualTo(message.timestamp)
        assertThat(response[1].content).isEqualTo(message.content)

        messageRepository.delete(message)
        messageRepository.delete(message2)
    }

    @Test
    fun `should get all messages by sender id`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello, how are you?"

        val message2 = Message()
        message2.idChat = 2
        message2.idSender = "johndoe@gmail.com"
        message2.timestamp = LocalDateTime.of(2022, 5, 5, 11, 0)
        message2.content = "I'm fine, thanks!"

        messageRepository.saveAll(listOf(message, message2))

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/messages/sender/${message.idSender}",
            Array<MessageDTO>::class.java
        )

        // Then
//        Should be descending order
        assertThat(response).isNotNull
        assertThat(response).hasSize(2)
        assertThat(response[0].idChat).isEqualTo(message2.idChat)
        assertThat(response[0].idSender).isEqualTo(message2.idSender)
        assertThat(response[0].timestamp).isEqualTo(message2.timestamp)
        assertThat(response[0].content).isEqualTo(message2.content)
        assertThat(response[1].idChat).isEqualTo(message.idChat)
        assertThat(response[1].idSender).isEqualTo(message.idSender)
        assertThat(response[1].timestamp).isEqualTo(message.timestamp)
        assertThat(response[1].content).isEqualTo(message.content)

        messageRepository.delete(message)
        messageRepository.delete(message2)
    }

    @Test
    fun `should create a message`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello, how are you?"

        // When
        val response = restTemplate.postForObject(
            "http://localhost:$port/API/messages",
            message,
            MessageDTO::class.java
        )

        // Then
        assertThat(response).isNotNull
        assertThat(response.mid).isNotNull
        assertThat(response.idChat).isEqualTo(message.idChat)
        assertThat(response.idSender).isEqualTo(message.idSender)
        assertThat(response.timestamp).isEqualTo(message.timestamp)
        assertThat(response.content).isEqualTo(message.content)

        messageRepository.delete(message)
    }

    @Test
    fun `should update a message`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello, how are you?"
        messageRepository.save(message)
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 11, 0)
        message.content = "I'm fine, thanks!"

        restTemplate.put(
            "http://localhost:$port/API/message",
            message,
            MessageDTO::class.java
        )

        // When
        val response = restTemplate.getForObject(
            "http://localhost:$port/API/message/${message.mid}",
            MessageDTO::class.java
        )

        // Then
        assertThat(response).isNotNull
        assertThat(response.mid).isEqualTo(message.mid)
        assertThat(response.idChat).isEqualTo(message.idChat)
        assertThat(response.idSender).isEqualTo(message.idSender)
        assertThat(response.timestamp).isEqualTo(message.timestamp)
        assertThat(response.content).isEqualTo(message.content)

        messageRepository.delete(message)
    }

    @Test
    fun `should delete a message`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello, how are you?"

        messageRepository.save(message)

        // When
        restTemplate.delete(
            "http://localhost:$port/API/message/${message.mid}"
        )

        // Then
        assertThat(messageRepository.findById(message.mid)).isEmpty
    }

    @Test
    fun `should delete all message by chat id`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello, how are you?"

        val message2 = Message()
        message2.idChat = 2
        message2.idSender = "johndoe@gmail.com"
        message2.timestamp = LocalDateTime.of(2022, 5, 5, 11, 0)
        message2.content = "I'm fine, thanks!"

        messageRepository.saveAll(listOf(message, message2))

        // When
        restTemplate.delete(
            "http://localhost:$port/API/messages/chat/${message.idChat}"
        )

        // Then
        assertThat(messageRepository.findById(message.mid)).isEmpty
        assertThat(messageRepository.findById(message2.mid)).isNotEmpty

        messageRepository.delete(message2)
    }

    @Test
    fun `should delete all messages`() {
        // Given
        val message = Message()
        message.idChat = 1
        message.idSender = "johndoe@gmail.com"
        message.timestamp = LocalDateTime.of(2022, 5, 4, 10, 0)
        message.content = "Hello, how are you?"

        val message2 = Message()
        message2.idChat = 1
        message2.idSender = "johndoe@gmail.com"
        message2.timestamp = LocalDateTime.of(2022, 5, 5, 11, 0)
        message2.content = "I'm fine, thanks!"

        messageRepository.saveAll(listOf(message, message2))

        // When
        restTemplate.delete(
            "http://localhost:$port/API/messages"
        )

        // Then
        assertThat(messageRepository.findById(message.mid)).isEmpty
        assertThat(messageRepository.findById(message2.mid)).isEmpty
    }
}
