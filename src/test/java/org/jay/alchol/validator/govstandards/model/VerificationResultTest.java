package org.jay.alchol.validator.govstandards.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerificationResultTest {

    @Test
    void testNeedsReviewLogic() {
        VerificationResult result = new VerificationResult();

        result.setBrandMatch(true);
        result.setAbvMatch(true);
        result.setNetContentsMatch(true);
        result.setWarningMatch(false);

        result.updateOverallStatus();

        assertTrue(result.isNeedsReview());
    }
}
