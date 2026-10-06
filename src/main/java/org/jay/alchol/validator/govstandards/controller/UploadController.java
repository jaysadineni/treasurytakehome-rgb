package org.jay.alchol.validator.govstandards.controller;

import org.jay.alchol.validator.govstandards.service.BatchProcessingService;
import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final BatchProcessingService batchService;

    public UploadController(BatchProcessingService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/batch")
    public ResponseEntity<List<VerificationResult>> processBatch(
            @RequestParam("files") List<MultipartFile> files,
            @ModelAttribute LabelApplication applicationData) {

        List<VerificationResult> results = batchService.processBatch(files, applicationData);
        return ResponseEntity.ok(results);
    }
}
