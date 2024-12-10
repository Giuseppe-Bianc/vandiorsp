package org.dersbian.vandiorsp;

/*import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletResponse;*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.context.request.WebRequest;

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
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
