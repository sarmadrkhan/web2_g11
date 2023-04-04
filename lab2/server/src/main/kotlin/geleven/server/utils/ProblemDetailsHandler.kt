package geleven.server.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ProblemDetailsHandler: ResponseEntityExceptionHandler() {
  @ExceptionHandler(NotFoundException::class)
  fun handleProductNotFound(e: NotFoundException) =
    ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.message!!))

  @ExceptionHandler(DuplicateException::class)
  fun handleDuplicateProduct(e: DuplicateException) =
    ResponseEntity.status(HttpStatus.CONFLICT)
      .body(ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.message!!))
}
