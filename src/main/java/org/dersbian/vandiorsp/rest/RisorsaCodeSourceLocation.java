package org.dersbian.vandiorsp.rest;

import org.dersbian.vandiorsq.models.CodeSourceLocation;
import org.dersbian.vandiorsq.service.CodeSourceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/code_source_locations", produces = {"application/json"})
public class RisorsaCodeSourceLocation {
    private final CodeSourceLocationService codeSourceLocationService;

    @Autowired
    public RisorsaCodeSourceLocation(CodeSourceLocationService codeSourceLocationService) {
        this.codeSourceLocationService = codeSourceLocationService;
    }

    @GetMapping(path = "/get/{id}", produces = "application/json")
    public CodeSourceLocation getCodeSourceLocationById(@PathVariable("id") Long id) {
        return this.codeSourceLocationService.findCodeSourceLocation(id);
    }

    @GetMapping(path = "/get", produces = "application/json")
    public List<CodeSourceLocation> getCodeSourceLocationById() {
        return this.codeSourceLocationService.getSourceLocations();
    }

}
