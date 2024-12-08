package org.dersbian.vandiorsp.service;

import lombok.extern.slf4j.Slf4j;
import org.dersbian.vandiorsq.models.CodeSourceLocation;
import org.dersbian.vandiorsq.repositories.CodeSourceLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CodeSourceLocationService {
    private final CodeSourceLocationRepository codeSourceLocationRepository;

    @Autowired
    public CodeSourceLocationService(CodeSourceLocationRepository codeSourceLocationRepository) {
        this.codeSourceLocationRepository = codeSourceLocationRepository;
    }


    public CodeSourceLocation findCodeSourceLocation(Long id) {
        Optional<CodeSourceLocation> codeSourceLocation = codeSourceLocationRepository.findById(id);
        if (codeSourceLocation.isEmpty()) {
            throw new IllegalArgumentException("Code source location with ID " + id + " not found.");
        } else {
            return codeSourceLocation.get();
        }
    }

    public List<CodeSourceLocation> getSourceLocations() {
        return codeSourceLocationRepository.findAll();
    }
}
