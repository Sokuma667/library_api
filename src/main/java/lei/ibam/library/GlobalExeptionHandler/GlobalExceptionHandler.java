package lei.ibam.library.GlobalExeptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleBookAlreadyExists(BookAlreadyExistsException ex) {
       ApiError apiError = new ApiError();
       apiError.setMessage(ex.getMessage());
       apiError.setCode(HttpStatus.NOT_FOUND.value());
       apiError.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT); // 409
    }


    @ExceptionHandler(BookNotExistsException.class)
    public ResponseEntity<ApiError> handleBookNotExists(BookNotExistsException ex) {

        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimestamp(LocalDateTime.now());
        // On renvoie le message de l'exception avec le status 404
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotExistsExeption.class)
    public ResponseEntity<ApiError> handleUserNotExists(UserNotExistsExeption ex) {

        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimestamp(LocalDateTime.now());
        // On renvoie le message de l'exception avec le status 404
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsExeption.class)
    public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExistsExeption ex) {

        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setCode(HttpStatus.CONFLICT.value());
        apiError.setTimestamp(LocalDateTime.now());
        // On renvoie le message de l'exception avec le status 404
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

}
