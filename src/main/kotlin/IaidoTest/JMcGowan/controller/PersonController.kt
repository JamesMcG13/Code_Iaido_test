package IaidoTest.JMcGowan.controller

import IaidoTest.JMcGowan.dto.PersonDTO
import IaidoTest.JMcGowan.entity.Person
import IaidoTest.JMcGowan.service.PersonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

// Basic controller functions again found from https://www.youtube.com/watch?v=AioHZDaL4Jw
// Done as pageable as requested by the specification

@RestController
@RequestMapping("/people")
@PreAuthorize("hasAnyRole('ADMIN')")
class PersonController(private val service: PersonService) {
    @PostMapping
    fun create(@RequestBody person: Person): Person = service.save(person)

    @GetMapping
    fun findAll(pageable: Pageable): Page<Person> = service.findAll(pageable)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Person> {
        val person = service.findById(id)
        return if (person != null) ResponseEntity.ok(person) else ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody updatedPerson: Person): ResponseEntity<Person> {
        return if (service.findById(id) != null) {
            ResponseEntity.ok(service.save(updatedPerson.copy(id = id)))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        return if (service.findById(id) != null) {
            service.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    fun filter(@RequestParam(required = false) name: String?,
               @RequestParam(required = false) age: Int?,
               pageable: Pageable): ResponseEntity<Page<PersonDTO>> {
        val people = service.findFiltered(name, age, pageable)
        val peopleDTO = people.map { person ->
            PersonDTO(
                name = person.name,
                surname = person.surname,
                email = person.email,
                phone = person.phone,
                dateOfBirth = person.dateOfBirth,
                age = person.age
            )
        }
        return ResponseEntity.ok(peopleDTO)
    }
}