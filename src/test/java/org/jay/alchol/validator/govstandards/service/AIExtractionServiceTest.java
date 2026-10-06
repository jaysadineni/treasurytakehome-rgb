package org.jay.alchol.validator.govstandards.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jay.alchol.validator.govstandards.model.ExtractedLabel;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AIExtractionServiceTest {

    @Test
    void testExtractFieldsSuccess() throws Exception {

        // Enable deep stubs so WebClient.post().uri().bodyValue().retrieve() works
        WebClient webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        ObjectMapper mapper = new ObjectMapper();

        MultipartFile file = mock(MultipartFile.class);
        when(file.getBytes()).thenReturn("image".getBytes());

        // Mock the final response
        when(webClient.post()
                .uri(anyString())
                .bodyValue(any())
                .retrieve()
                .bodyToMono(String.class))
                .thenReturn(Mono.just("{\"brand\":\"TestBrand\"}"));

        AIExtractionService service = new AIExtractionService(webClient, mapper);

        ExtractedLabel result = service.extractFields(file);

        assertEquals("TestBrand", result.getBrand());
    }

    @Test
    void testExtractFieldsFailure() throws Exception {
        WebClient webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        ObjectMapper mapper = new ObjectMapper();

        MultipartFile file = mock(MultipartFile.class);
        when(file.getBytes()).thenThrow(new RuntimeException("Bad file"));

        AIExtractionService service = new AIExtractionService(webClient, mapper);

        assertThrows(RuntimeException.class, () -> service.extractFields(file));
    }
}
