package org.jay.alchol.validator.govstandards.util;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;

public class FuzzyMatcher {

    private static final JaroWinklerSimilarity SIMILARITY = new JaroWinklerSimilarity();

    public static boolean compare(String expected, String actual) {
        if (expected == null || actual == null) return false;
        double score = SIMILARITY.apply(expected, actual);
        return score > 0.85;
    }
}
