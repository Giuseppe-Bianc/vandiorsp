package org.dersbian.vandiorsp.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta. validation. constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest {
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank
    @Size(min=6)
    private String password;
}
