package org.dersbian.vandiorsp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dersbian.vandiorsp.model.FileName;
import org.dersbian.vandiorsp.repository.FileNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FileNameService {
    private final FileNameRepository fileNameRepository;
    public List<FileName> getFileNames() {
        return fileNameRepository.findAll();
    }

    public FileName findFileName(Long id) {
        return fileNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("FileName with ID: " + id + " not found"));

    }
}
