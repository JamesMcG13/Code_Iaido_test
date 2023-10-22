package IaidoTest.JMcGowan.entity

import jakarta.persistence.*
import java.util.*

// Basic Entity setup for Person as found here https://www.youtube.com/watch?v=AioHZDaL4Jw
@Entity
data class Person(
        @Id @GeneratedValue val id: Long? = null,
        val name: String,
        val surname: String,
        val email: String,
        val phone: String,
        val dateOfBirth: Date,
        val age: Int,
        val username: String,
        val password: String
)