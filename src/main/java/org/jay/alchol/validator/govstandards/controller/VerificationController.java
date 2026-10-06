package org.jay.alchol.validator.govstandards.controller;

import org.jay.alchol.validator.govstandards.model.LabelApplication;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.jay.alchol.validator.govstandards.model.VerificationResult;
import org.jay.alchol.validator.govstandards.service.VerificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping
    public VerificationResult verify(
            @RequestBody LabelApplication app,
            @RequestBody ExtractedLabel extracted) {

        return verificationService.verify(app, extracted);
    }
}
