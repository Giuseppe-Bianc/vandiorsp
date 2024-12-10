package org.dersbian.vandiorsp.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String errorMessage;
    private String firstname;
    private String lastname;
    private String email;

    public static AuthenticationResponse error(String errorMessage) {
        return AuthenticationResponse.builder()
                .errorMessage(errorMessage)
                .build();
    }

    public static AuthenticationResponse success(String token, String firstname, String lastname, String email) {
        return AuthenticationResponse.builder()
                .token(token)
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .build();
    }
}
