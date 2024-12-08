package org.dersbian.vandiorsp.rest;

import lombok.AllArgsConstructor;
import org.dersbian.vandiorsp.model.dto.TokenSummaryDTO;
import org.dersbian.vandiorsp.service.TokenSummaryService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/token-summary", produces = {"application/json"})
public class RisorsaTokenSummary {
    private final TokenSummaryService tokenSummaryService;

    @PutMapping(path = "/save")
    public List<TokenSummaryDTO> saveTokenSummaries() {
        return this.tokenSummaryService.saveTokenSummaries();
    }
}
