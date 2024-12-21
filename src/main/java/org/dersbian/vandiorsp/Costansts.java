package org.dersbian.vandiorsp;

public class Costansts {
    public static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };
    public static final String ALLOWED_ORIGIN_LOCAL = "http://localhost:4200";
    public static final String ALLOWED_ORIGIN_NULL = "null";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final int BEARER_PREFIX_LEN = BEARER_PREFIX.length();
}
