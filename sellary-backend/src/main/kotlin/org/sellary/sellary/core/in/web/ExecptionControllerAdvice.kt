package org.sellary.sellary.core.`in`.web

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    private val log = KotlinLogging.logger {}

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(e: EmptyResultDataAccessException): ResponseEntity<ErrorResponse> =
        notFoundResponse(e)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<ErrorResponse> =
        notFoundResponse(e)

    private fun notFoundResponse(e: Exception): ResponseEntity<ErrorResponse> {
        log.warn { "not found exception occurred: ${e.message}" }
        return ResponseEntity
            .status(404)
            .body(
                ErrorResponse(
                    message = "해당 정보를 찾을 수가 없습니다"
                )
            )
    }
}

data class ErrorResponse(
    val message: String,
)