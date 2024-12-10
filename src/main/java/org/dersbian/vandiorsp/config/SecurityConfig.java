package org.dersbian.vandiorsp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dersbian.vandiorsp.CustomErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.dersbian.vandiorsp.Costansts.*;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    /*@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Define allowed origins (you can list specific origins)
        config.setAllowedOrigins(Arrays.asList(ALLOWED_ORIGIN_LOCAL, ALLOWED_ORIGIN_NULL));

        // Define allowed methods (GET, POST, PUT, DELETE, etc.)
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

        // Define allowed headers
        config.setAllowedHeaders(Arrays.asList("origin", "content-type", "accept", "authorization"));

        // Enable credentials if needed (cookies, authorization headers, etc.)
        config.setAllowCredentials(true);

        // Register the CORS configuration for all paths
        source.registerCorsConfiguration("/**", config);
        return source;
    }
*/
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(ALLOWED_ORIGIN_LOCAL, ALLOWED_ORIGIN_NULL) // Frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated()
                ).csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /*.exceptionHandling(exceptionHandling -> exceptionHandling.
                        authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("Authentication Failed: " + authException.getMessage());
                        })
                        authenticationEntryPoint((request, response, authException) -> {
                            // Create a CustomErrorResponse
                            CustomErrorResponse errorResponse = new CustomErrorResponse(
                                    "Authentication Failed: " + authException.getMessage(),
                                    request.getRequestURI()
                            );

                            // Return a ResponseEntity with the error details
                            ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);

                            // Write the response to the HttpServletResponse
                            response.setStatus(responseEntity.getStatusCodeValue());
                            response.setContentType("application/json");
                            response.getWriter().write(errorResponse.toString());
                        })
                        //.authenticationEntryPoint(customAuthenticationEntryPoint)
                )*/
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");

                    String  path = request.getRequestURI();

                    // Create a CustomErrorResponse with additional information
                    CustomErrorResponse errorResponse = new CustomErrorResponse(
                            "Authentication Failed: " + authException.getMessage(),
                            path
                    );

                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    objectMapper.writeValue(response.getWriter(), errorResponse);
                }))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
