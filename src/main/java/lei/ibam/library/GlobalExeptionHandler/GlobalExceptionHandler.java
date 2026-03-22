package lei.ibam.library.GlobalExeptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    private InvalidFormatException extractInvalidFormat(Throwable t) {
        if (t == null) return null;

        if (t instanceof InvalidFormatException ife) {
            return ife;
        }

        // Jackson peut encapsuler InvalidFormatException dans MismatchedInputException
        if (t instanceof JsonMappingException jme && jme.getCause() instanceof InvalidFormatException) {
            return (InvalidFormatException) jme.getCause();
        }

        return extractInvalidFormat(t.getCause());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = "Requête invalide";

        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setCode(HttpStatus.BAD_REQUEST.value());
        apiError.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(BookCategoryNotExistsException.class)
    public ResponseEntity<ApiError> handleBookCategoryNotExistsException(BookCategoryNotExistsException ex) {

        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimestamp(LocalDateTime.now());
        // On renvoie le message de l'exception avec le status 404
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotInStockException.class)
    public ResponseEntity<ApiError> handleBookNotInStockException(BookNotInStockException ex) {

        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setCode(HttpStatus.REQUEST_TIMEOUT.value());
        apiError.setTimestamp(LocalDateTime.now());
        // On renvoie le message de l'exception avec le status 404
        return new ResponseEntity<>(apiError, HttpStatus.REQUEST_TIMEOUT);
    }
}
