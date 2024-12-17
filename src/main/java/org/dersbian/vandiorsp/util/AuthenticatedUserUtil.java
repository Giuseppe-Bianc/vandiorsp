package org.dersbian.vandiorsp.util;

import org.dersbian.vandiorsp.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuthenticatedUserUtil {
    public static Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof User user) {
                return Optional.of(user); // Your custom User class
            } else if (principal instanceof UserDetails userDetails) {
                // Cast to UserDetails if principal is not your custom User
                // Assuming your UserDetails implementation matches User
                return Optional.of((User) userDetails);
            }
        }
        return Optional.empty(); // No authenticated user or invalid principal
    }
}
