package IaidoTest.JMcGowan.dto

import java.util.*
// I created a DTO so that when a Person entity is retrieved the username and password are kept hidden

data class PersonDTO(
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val dateOfBirth: Date,
    val age: Int
)