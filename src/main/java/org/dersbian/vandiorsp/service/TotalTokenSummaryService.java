package org.dersbian.vandiorsp.service;

import lombok.extern.slf4j.Slf4j;
import org.dersbian.vandiorsp.model.TotalTokenSummary;
import org.dersbian.vandiorsp.repository.TotalTokenSummaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TotalTokenSummaryService {

    private final TotalTokenSummaryRepository totalTokenSummaryRepository;


    public TotalTokenSummaryService(TotalTokenSummaryRepository totalTokenSummaryRepository) {
        this.totalTokenSummaryRepository = totalTokenSummaryRepository;
    }

    public void saveTotalTokenSummaries() {
        List<TotalTokenSummary> totalTokenSummaries = totalTokenSummaryRepository.findTokenSummaries();
        totalTokenSummaryRepository.saveAll(totalTokenSummaries);
    }
}
