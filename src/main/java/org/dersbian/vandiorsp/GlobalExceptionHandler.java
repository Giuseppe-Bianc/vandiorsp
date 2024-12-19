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

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private String extractPath(WebRequest request) {
        String description = request.getDescription(false);
        return description.substring(4);
    }

    // Handle IllegalArgumentException specifically
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Set the HTTP status to 400 Bad Request
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "Bad Request: " + ex.getMessage(),
                extractPath(request)  // Get the request path
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                extractPath(request)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "Bad credentials: " + ex.getMessage(),
                extractPath(request)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "This API endpoint is not found: " + ex.getMessage(),
                extractPath(request)  // Get the request path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle LockedException
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<CustomErrorResponse> handleLockedException(LockedException ex, WebRequest request) {
        String path = extractPath(request);
        CustomErrorResponse errorResponse = new CustomErrorResponse("Account is locked: " + ex.getMessage(), path);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Handle DisabledException
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<CustomErrorResponse> handleDisabledException(DisabledException ex, WebRequest request) {
        String path = extractPath(request);
        CustomErrorResponse errorResponse = new CustomErrorResponse("Account is disabled: " + ex.getMessage(), path);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomValidationExceptionsResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        CustomValidationExceptionsResponse errorResponse = new CustomValidationExceptionsResponse(extractPath(request));
        ex.getBindingResult().getFieldErrors().forEach(error -> errorResponse.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> handleIOException(IOException ex, WebRequest request) {
        var errorResponse = new CustomErrorResponse("IO Error: " + ex.getMessage(), extractPath(request));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> handleOtherException(Exception ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                "A server internal error occurs: " + ex.getMessage(),
                extractPath(request)  // Get the request path
        );
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
