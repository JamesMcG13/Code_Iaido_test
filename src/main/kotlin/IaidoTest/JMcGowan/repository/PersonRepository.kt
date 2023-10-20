package IaidoTest.JMcGowan.repository

import IaidoTest.JMcGowan.entity.Person
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

// Interface for the non-standard CRUD requests returned in the "/filter" URL
@Repository
interface PersonRepository : JpaRepository<Person, Long>{
    fun findByNameContaining(name: String, pageable: Pageable): Page<Person>
    fun findByAge(age: Int, pageable: Pageable): Page<Person>
    fun findByNameContainingAndAge(name: String, age: Int, pageable: Pageable): Page<Person>
}