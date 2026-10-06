package org.jay.alchol.validator.govstandards.model;

import lombok.Data;

@Data
public class VerificationResult {

    private boolean brandMatch;
    private boolean abvMatch;
    private boolean netContentsMatch;
    private boolean warningMatch;

    private boolean needsReview;

    public void updateOverallStatus() {
        this.needsReview = !(brandMatch && abvMatch && netContentsMatch && warningMatch);
    }
}
