package org.dersbian.vandiorsp.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.dersbian.vandiorsp.model.Token;
import org.dersbian.vandiorsp.service.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "api/v1/tokens", produces = {"application/json"})
public class RisorsaToken {

    private final TokenService tokenService;

    /**
     * Saves a new token.
     * <p>
     * This endpoint allows clients to store a new token in the database.
     * The token must contain valid data as defined by the Token model.
     *
     * @param newToken The token to be saved. Must not be null.
     * @return The ID of the saved token.
     */
    // Controller - More detailed documentation
    @Operation(
            summary = "Save a new token to the database",
            description = "This operation saves a new token to the database. The token must include a valid type, value, and an associated source location. "
                    + "The token is expected to have complete and properly formatted data and the id must be uninitialized to be stored successfully."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token saved successfully. The response includes the unique ID of the saved token."),
            @ApiResponse(responseCode = "400", description = "Invalid token data provided. This error occurs if required fields such as 'type', 'value', or 'source location' are missing, or if they are improperly formatted. Ensure all fields in the request body adhere to the expected schema."),
            @ApiResponse(responseCode = "500", description = "Internal server error. This error may arise due to unexpected issues when saving the token, such as database connection problems.")
    })
    @PutMapping(path = "/new")
    public Long saveToken(@RequestBody Token newToken) {
        this.tokenService.saveToken(newToken);
        return newToken.getId();
    }


    /**
     * Retrieves a token by its ID.
     * <p>
     * This endpoint fetches a specific token from the database using its unique identifier.
     * If the token does not exist, an appropriate error response is returned.
     *
     * @param id The ID of the token. Must be a valid long value.
     * @return The requested token if found; otherwise, an error response.
     */
    @Operation(
            summary = "Retrieve a token by ID",
            description = "Fetches a token from the database by its unique ID. Returns 404 if not found."
    )
    @Parameter(name = "id", description = "The unique ID of the token to be retrieved", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token found and returned successfully"),
            @ApiResponse(responseCode = "404", description = "Token not found with the given ID")
    })
    @GetMapping(path = "/get/{id}", produces = "application/json")
    public Token getToken(@PathVariable("id") Long id) {
        return tokenService.findToken(id);
    }

    /**
     * Retrieves all tokens.
     * <p>
     * This endpoint returns a list of all tokens stored in the database.
     * Useful for administrative purposes or auditing.
     *
     * @return A list of all tokens.
     */
    @Operation(
            summary = "Retrieve all tokens",
            description = "Fetches all tokens stored in the database."
    )
    @ApiResponse(responseCode = "200", description = "List of tokens returned successfully")
    @GetMapping(path = "/get", produces = "application/json")
    public List<Token> getTokens() {
        return tokenService.getTokens();
    }

    /*@GetMapping(path = "/csrf-token", produces = "application/json")
    public CsrfToken getCrsfAutToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }*/

    @Operation(
            summary = "Parse the input file and save a list token parsed from the input file",
            description = "Save a list token parsed from the input file."
    )
    @ApiResponse(responseCode = "200", description = "List of tokens returned successfully")
    @PostMapping(value = "/process-file", consumes = "multipart/form-data", produces = "application/json")
    public List<Token> parseFile(@RequestParam("file") MultipartFile file) throws IOException {
        return tokenService.parseFile(file);
    }
}