package org.jay.alchol.validator.govstandards.util;

public class GovernmentWarningValidator {

    private static final String REQUIRED_WARNING =
            "GOVERNMENT WARNING: According to the Surgeon General, women should not drink alcoholic beverages during pregnancy because of the risk of birth defects.";

    public static boolean isExact(String actual) {
        return REQUIRED_WARNING.equals(actual);
    }
}
