package geleven.server.utils

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : RuntimeException(message)
class DuplicateException(message: String) : RuntimeException(message)
data class ProblemDetail(val status: Int, val detail: String) {
  companion object {
    fun forStatusAndDetail(status: HttpStatus, detail: String) =
      ProblemDetail(status.value(), detail)
  }
}