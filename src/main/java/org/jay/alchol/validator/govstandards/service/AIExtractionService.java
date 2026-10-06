package org.jay.alchol.validator.govstandards.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
public class AIExtractionService {

    private final WebClient webClient;
    private final ObjectMapper mapper;

    public AIExtractionService(WebClient webClient, ObjectMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
    }

    public ExtractedLabel extractFields(MultipartFile image) {
        try {
            String base64 = Base64.getEncoder().encodeToString(image.getBytes());

            String response = webClient.post()
                    .uri("/ai/extract")
                    .bodyValue(base64)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return mapper.readValue(response, ExtractedLabel.class);

        } catch (Exception e) {
            throw new RuntimeException("AI extraction failed", e);
        }
    }
}
