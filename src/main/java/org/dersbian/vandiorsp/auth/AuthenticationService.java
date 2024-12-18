package org.dersbian.vandiorsp.auth;

import lombok.RequiredArgsConstructor;
import org.dersbian.vandiorsp.config.JwtService;
import org.dersbian.vandiorsp.user.Role;
import org.dersbian.vandiorsp.user.User;
import org.dersbian.vandiorsp.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        repository.findByEmail(request.getEmail()).ifPresent(_ -> {
            throw new IllegalArgumentException("User email already exists: " + request.getEmail());
        });

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        try {
            // Perform authentication
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid email or password.");
        }

        // Find the user
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.getEmail()));


        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }
}
