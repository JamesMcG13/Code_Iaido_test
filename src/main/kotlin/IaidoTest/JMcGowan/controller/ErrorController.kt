package IaidoTest.JMcGowan.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ErrorController {
    @RequestMapping("/unauthorized")
    fun unauthorized(): ResponseEntity<String> {
        return ResponseEntity("You are not authorized to view this content.", HttpStatus.FORBIDDEN)
    }
}