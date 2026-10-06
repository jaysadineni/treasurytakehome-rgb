package org.jay.alchol.validator.govstandards.service;

import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class BatchProcessingService {

    private final AIExtractionService aiService;
    private final VerificationService verificationService;
    private final ExecutorService executor = Executors.newFixedThreadPool(20);

    public BatchProcessingService(AIExtractionService aiService,
                                  VerificationService verificationService) {
        this.aiService = aiService;
        this.verificationService = verificationService;
    }

    public List<VerificationResult> processBatch(List<MultipartFile> images,
                                                 LabelApplication app) {

        List<CompletableFuture<VerificationResult>> futures = images.stream()
                .map(img -> CompletableFuture.supplyAsync(() -> {
                    ExtractedLabel extracted = aiService.extractFields(img);
                    return verificationService.verify(app, extracted);
                }, executor))
                .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
