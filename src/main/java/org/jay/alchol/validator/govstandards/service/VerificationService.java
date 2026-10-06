package org.jay.alchol.validator.govstandards.service;

import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.jay.alchol.validator.govstandards.util.FuzzyMatcher;
import org.jay.alchol.validator.govstandards.util.GovernmentWarningValidator;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    public VerificationResult verify(LabelApplication app, ExtractedLabel extracted) {
        VerificationResult result = new VerificationResult();

        result.setBrandMatch(FuzzyMatcher.compare(app.getBrand(), extracted.getBrand()));
        result.setAbvMatch(app.getAbv().equals(extracted.getAbv()));
        result.setNetContentsMatch(FuzzyMatcher.compare(app.getNetContents(), extracted.getNetContents()));
        result.setWarningMatch(GovernmentWarningValidator.isExact(extracted.getWarning()));

        result.updateOverallStatus();

        return result;
    }
}
