package devRev.user;

import devRev.dto.ErrorResponse;
import devRev.user.exception.InValidEmailException;
import devRev.user.exception.InValidMobileNumberException;
import devRev.user.exception.InValidPasswordException;
import devRev.user.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleItemAlreadyExistsException(UserAlreadyExistException userAlreadyExist) {

        ErrorResponse errorResponse = new ErrorResponse(userAlreadyExist.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(InValidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInValidPasswordException(InValidPasswordException inValidPasswordException) {
        ErrorResponse errorResponse = new ErrorResponse(inValidPasswordException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(InValidEmailException.class)
    public ResponseEntity<ErrorResponse> handleInValidEmailException(InValidEmailException inValidEmailException) {
        ErrorResponse errorResponse = new ErrorResponse(inValidEmailException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(InValidMobileNumberException.class)
    public ResponseEntity<ErrorResponse> handleInValidMobileNumberException(InValidMobileNumberException inValidMobileNumberException) {
        ErrorResponse errorResponse = new ErrorResponse(inValidMobileNumberException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        ErrorResponse errorResponse = new ErrorResponse(illegalArgumentException.getMessage());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorResponse);
    }
}
