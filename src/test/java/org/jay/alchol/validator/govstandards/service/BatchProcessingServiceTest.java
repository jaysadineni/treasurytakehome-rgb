package org.jay.alchol.validator.govstandards.service;

import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BatchProcessingServiceTest {

    @Test
    void testParallelBatchProcessing() {
        AIExtractionService ai = mock(AIExtractionService.class);
        VerificationService verify = mock(VerificationService.class);

        BatchProcessingService service = new BatchProcessingService(ai, verify);

        MultipartFile file1 = mock(MultipartFile.class);
        MultipartFile file2 = mock(MultipartFile.class);

        ExtractedLabel extracted = new ExtractedLabel();
        VerificationResult result = new VerificationResult();

        when(ai.extractFields(any())).thenReturn(extracted);
        when(verify.verify(any(), any())).thenReturn(result);

        List<VerificationResult> results =
                service.processBatch(List.of(file1, file2), new LabelApplication());

        assertEquals(2, results.size());
        verify(ai, times(2)).extractFields(any());
        verify(verify, times(2)).verify(any(), any());
    }
}
