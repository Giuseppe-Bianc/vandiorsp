package org.dersbian.vandiorsp.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(path = "/register",consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @NonNull @Valid RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(path ="/authenticate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @NonNull @Valid AuthenticateRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
