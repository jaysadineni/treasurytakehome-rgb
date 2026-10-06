package org.jay.alchol.validator.govstandards.controller;

import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.jay.alchol.validator.govstandards.service.BatchProcessingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UploadControllerTest {

    @Test
    void testProcessBatch() {
        BatchProcessingService batchService = mock(BatchProcessingService.class);
        UploadController controller = new UploadController(batchService);

        MultipartFile file = mock(MultipartFile.class);
        LabelApplication app = new LabelApplication();

        VerificationResult result = new VerificationResult();
        when(batchService.processBatch(List.of(file), app)).thenReturn(List.of(result));

        ResponseEntity<List<VerificationResult>> response =
                controller.processBatch(List.of(file), app);

        assertEquals(1, response.getBody().size());
        verify(batchService, times(1)).processBatch(List.of(file), app);
    }
}
