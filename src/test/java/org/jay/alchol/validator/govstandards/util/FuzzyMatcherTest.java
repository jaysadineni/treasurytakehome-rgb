package org.jay.alchol.validator.govstandards.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuzzyMatcherTest {

    @Test
    void testSimilarStringsMatch() {
        assertTrue(FuzzyMatcher.compare("Stone", "Stoné"));
    }

    @Test
    void testDifferentStringsFail() {
        assertFalse(FuzzyMatcher.compare("Stone", "Whiskey"));
    }
}
