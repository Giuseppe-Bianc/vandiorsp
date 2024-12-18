package org.dersbian.vandiorsp;

/*import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletResponse;*/

import org.dersbian.vandiorsp.auth.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "Authentication Failed: " + ex.getMessage(),
                request.getDescription(false)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }*/
    // Handle IllegalArgumentException specifically
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Set the HTTP status to 400 Bad Request
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "Bad Request: " + ex.getMessage(),
                request.getDescription(false).substring(4)  // Get the request path
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                request.getDescription(false).substring(4)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "Bad credentials: " + ex.getMessage(),
                request.getDescription(false).substring(4)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "This API endpoint is not found: " + ex.getMessage(),
                request.getDescription(false).substring(4)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle LockedException
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<CustomErrorResponse> handleLockedException(LockedException ex, WebRequest request) {
        String path = request.getDescription(false).substring(4);
        CustomErrorResponse errorResponse = new CustomErrorResponse("Account is locked: " + ex.getMessage(), path);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Handle DisabledException
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<CustomErrorResponse> handleDisabledException(DisabledException ex, WebRequest request) {
        String path = request.getDescription(false).substring(4);
        CustomErrorResponse errorResponse = new CustomErrorResponse("Account is disabled: " + ex.getMessage(), path);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomValidationExceptionsResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        CustomValidationExceptionsResponse errorResponse = new CustomValidationExceptionsResponse(
                request.getDescription(false).substring(4)  // Get the request path
        );
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorResponse.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> handleOtherException(Exception ex, WebRequest request) {
        //return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "A server internal error occurs.", ex.getMessage());
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "A server internal error occurs: " + ex.getMessage(),
                request.getDescription(false).substring(4)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
