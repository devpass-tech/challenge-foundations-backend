package io.devpass.parky

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import com.github.kittinunf.fuel.jackson.objectBody
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.UUID

@SpringBootApplication
class ParkyApplication

data class Book(
    val bookId: String,
    val title: String,
    val author: String,
    val pages: Int,
)

data class BookCreationRequest(
    val title: String,
    val author: String,
    val pages: Int,
)

fun main(args: Array<String>) {
    runApplication<ParkyApplication>(*args)

    createBook()

    val (_, result, response) = Fuel.get("http://localhost:7070/books").responseObject<List<Book>>(jacksonDeserializerOf())
    if (result.isSuccessful) {
        response.get().forEach {
            println(it)
        }
    }
}

fun createBook() {
    val bookCreationRequest = BookCreationRequest("Aaaa", "Yooo", 10)
    val (_, result, response) = Fuel.post("http://localhost:7070/books").objectBody(bookCreationRequest).responseObject<Book>(jacksonDeserializerOf())
    if (result.isSuccessful) {
        println("Deu boa pra criar o novo livro!")
    }
}
