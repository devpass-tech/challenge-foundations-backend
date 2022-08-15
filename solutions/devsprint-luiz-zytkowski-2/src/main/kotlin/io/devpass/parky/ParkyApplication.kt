package io.devpass.parky

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParkyApplication

fun main(args: Array<String>) {
	runApplication<ParkyApplication>(*args)
}
