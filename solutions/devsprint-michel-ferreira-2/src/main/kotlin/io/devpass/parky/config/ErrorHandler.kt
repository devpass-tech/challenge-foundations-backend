package io.devpass.parky.config

import io.devpass.parky.framework.OwnedException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    data class DefaultError(
        var exceptionType: String,
        var message: String,
    )

    @ExceptionHandler(value = [OwnedException::class])
    protected fun handleOwnedException(ex: OwnedException): ResponseEntity<DefaultError> {
        return ResponseEntity.badRequest().body(
            DefaultError(
                exceptionType = ex.javaClass.canonicalName ?: "UnnamedErrorClass",
                message = ex.message ?: "Unexpected error",
            )
        )
    }

    @ExceptionHandler(value = [Exception::class])
    protected fun handleDefaultException(ex: Exception): ResponseEntity<DefaultError> {
        logger.error(ex.message, ex)
        return ResponseEntity.internalServerError().body(
            DefaultError(
                exceptionType = ex.javaClass.canonicalName ?: "UnnamedErrorClass",
                message = "Something went wrong. We're already figuring it out.",
            )
        )
    }

}
