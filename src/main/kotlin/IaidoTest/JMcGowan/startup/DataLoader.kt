package IaidoTest.JMcGowan.startup
import IaidoTest.JMcGowan.entity.Person
import IaidoTest.JMcGowan.repository.PersonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.Date

@Component
class DataLoader(private val personRepository: PersonRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        // Checking if data is already present to prevent duplication on every restart
        if (personRepository.count() == 0L) {
            val defaultUsers = listOf(
                Person(
                    name = "John",
                    surname = "Doe",
                    email = "john@example.com",
                    phone = "1234567890",
                    dateOfBirth = Date(90, 0, 1),  // 1990-01-01
                    age = 33,
                    username = "john_doe",
                    password = "pass123"
                ),
                Person(
                    name = "Jane",
                    surname = "Doe",
                    email = "jane@example.com",
                    phone = "0987654321",
                    dateOfBirth = Date(92, 4, 12),  // 1992-05-12
                    age = 31,
                    username = "jane_doe",
                    password = "pass456"
                ),
                Person(
                    name = "Alice",
                    surname = "Smith",
                    email = "alice@example.com",
                    phone = "1122334455",
                    dateOfBirth = Date(89, 7, 20),  // 1989-08-20
                    age = 34,
                    username = "alice_smith",
                    password = "pass789"
                ),
                Person(
                    name = "Bob",
                    surname = "Johnson",
                    email = "bob@example.com",
                    phone = "6677889900",
                    dateOfBirth = Date(87, 2, 14),  // 1987-03-14
                    age = 36,
                    username = "bob_johnson",
                    password = "pass012"
                ),
                Person(
                    name = "Charlie",
                    surname = "Brown",
                    email = "charlie@example.com",
                    phone = "2233445566",
                    dateOfBirth = Date(93, 10, 25),  // 1993-11-25
                    age = 30,
                    username = "charlie_brown",
                    password = "pass345"
                )
            )
            personRepository.saveAll(defaultUsers)
        }
    }
}