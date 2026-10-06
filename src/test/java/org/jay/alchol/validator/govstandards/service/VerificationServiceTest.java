package org.jay.alchol.validator.govstandards.service;

import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerificationServiceTest {

    @Test
    void testVerificationPass() {
        VerificationService service = new VerificationService();

        LabelApplication app = new LabelApplication();
        app.setBrand("Stone");
        app.setAbv("13%");
        app.setNetContents("750ml");
        app.setGovernmentWarning("GOVERNMENT WARNING: According to the Surgeon General, women should not drink alcoholic beverages during pregnancy because of the risk of birth defects.");

        ExtractedLabel extracted = new ExtractedLabel();
        extracted.setBrand("Stone");
        extracted.setAbv("13%");
        
        extracted.setNetContents("750ml");
        extracted.setWarning(app.getGovernmentWarning());

        VerificationResult result = service.verify(app, extracted);

        assertFalse(result.isNeedsReview());
    }

    @Test
    void testVerificationMismatch() {
        VerificationService service = new VerificationService();

        LabelApplication app = new LabelApplication();
        app.setBrand("Stone");
        app.setAbv("13%");

        ExtractedLabel extracted = new ExtractedLabel();
        extracted.setBrand("Wrong");
        extracted.setAbv("12%");

        VerificationResult result = service.verify(app, extracted);

        assertTrue(result.isNeedsReview());
    }
}
