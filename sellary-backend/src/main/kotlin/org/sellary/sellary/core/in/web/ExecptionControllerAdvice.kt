package org.sellary.sellary.core.`in`.web

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors


@RestControllerAdvice
class ExceptionControllerAdvice {

    private val log = KotlinLogging.logger {}

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        log.warn { "validation exception occurred: ${e.message}" }
        val errors = e.bindingResult.fieldErrors.stream()
            .map { error: FieldError -> error.field + ": " + error.defaultMessage }
            .collect(Collectors.toList())

        val errorResponse = ErrorResponse(
            message = "유효성 검증 오류가 발생했습니다. 실패목록: $errors",
        )

        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        log.warn { "bad request: cause - ${e.cause}, message - ${e.message}" }
        return ResponseEntity
            .badRequest()
            .body(ErrorResponse(message = e.message ?: "잘못된 접근입니다"))
    }

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