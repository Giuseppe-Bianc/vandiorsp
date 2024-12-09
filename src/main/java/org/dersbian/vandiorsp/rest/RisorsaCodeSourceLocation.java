package org.dersbian.vandiorsp.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.dersbian.vandiorsp.model.CodeSourceLocation;
import org.dersbian.vandiorsp.service.CodeSourceLocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/code_source_locations", produces = {"application/json"})
@SecurityRequirement(name = "bearerAuth")
public class RisorsaCodeSourceLocation {
    private final CodeSourceLocationService codeSourceLocationService;

    @GetMapping(path = "/get/{id}", produces = "application/json")
    public CodeSourceLocation getCodeSourceLocationById(@PathVariable("id") Long id) {
        return this.codeSourceLocationService.findCodeSourceLocation(id);
    }

    @GetMapping(path = "/get", produces = "application/json")
    public List<CodeSourceLocation> getCodeSourceLocationById() {
        return this.codeSourceLocationService.getSourceLocations();
    }

}
