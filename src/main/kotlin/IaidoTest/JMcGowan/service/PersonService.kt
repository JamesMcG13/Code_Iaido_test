package IaidoTest.JMcGowan.service

import IaidoTest.JMcGowan.entity.Person
import IaidoTest.JMcGowan.repository.PersonRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PersonService(private val repository: PersonRepository) {
    // Basic CRUD functions tested with Postman
    fun save(person: Person): Person = repository.save(person)
    fun findAll(pageable: Pageable): Page<Person> = repository.findAll(pageable)
    fun findById(id: Long): Person? = repository.findById(id).orElse(null)
    fun deleteById(id: Long) = repository.deleteById(id)

    // Filter by Name and/or Age
    fun findFiltered(name: String?, age: Int?, pageable: Pageable): Page<Person> {
        return when {
            name != null && age != null -> repository.findByNameContainingAndAge(name, age, pageable)
            name != null -> repository.findByNameContaining(name, pageable)
            age != null -> repository.findByAge(age, pageable)
            else -> repository.findAll(pageable)
        }
    }
}