package org.jay.alchol.validator.govstandards.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GovernmentWarningValidatorTest {

    @Test
    void testExactMatch() {
        String required = "GOVERNMENT WARNING: According to the Surgeon General, women should not drink alcoholic beverages during pregnancy because of the risk of birth defects.";
        assertTrue(GovernmentWarningValidator.isExact(required));
    }

    @Test
    void testCaseSensitiveFail() {
        assertFalse(GovernmentWarningValidator.isExact("government warning: according..."));
    }
}
