package org.jay.alchol.validator.govstandards.controller;

import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.jay.alchol.validator.govstandards.service.VerificationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerificationControllerTest {

    @Test
    void testVerify() {
        VerificationService service = mock(VerificationService.class);
        VerificationController controller = new VerificationController(service);

        LabelApplication app = new LabelApplication();
        ExtractedLabel extracted = new ExtractedLabel();
        VerificationResult expected = new VerificationResult();

        when(service.verify(app, extracted)).thenReturn(expected);

        VerificationResult actual = controller.verify(app, extracted);

        assertSame(expected, actual);
        verify(service, times(1)).verify(app, extracted);
    }
}
