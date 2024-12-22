package org.dersbian.vandiorsp.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.dersbian.vandiorsp.model.FileName;
import org.dersbian.vandiorsp.model.Token;
import org.dersbian.vandiorsp.service.FileNameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "api/v1/file_name", produces = {"application/json"})
public class RisorsaFileName {
    private FileNameService fileNameService;

    @GetMapping(path = "/get", produces = "application/json")
    public List<FileName> getTokens() {
        return fileNameService.getFileNames();
    }
}
