package IaidoTest.JMcGowan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JMcGowanApplication{
	fun helloWorld(): String = "Hello World!"
}

fun main(args: Array<String>) {
	runApplication<JMcGowanApplication>(*args)
}
