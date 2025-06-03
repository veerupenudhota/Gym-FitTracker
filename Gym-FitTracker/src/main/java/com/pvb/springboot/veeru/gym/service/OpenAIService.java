package com.pvb.springboot.veeru.gym.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class OpenAIService {

    @Value("${openai.api.key}") 
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper(); 
    private static final String DEFAULT_CHAT_MODEL = "gpt-4o-mini-2024-07-18"; 
    public String ask(String prompt, String model, String role) {
        String apiUrl = "https://api.openai.com/v1/chat/completions"; 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("temperature", 0.7); 
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", role, "content", prompt));
        requestBody.put("messages", messages);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (choices != null && !choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    if (message != null && message.containsKey("content")) {
                        return ((String) message.get("content")).trim();
                    } else {
                        throw new RuntimeException("AI response message content is missing.");
                    }
                } else {
                    throw new RuntimeException("No choices returned in AI response.");
                }
            } else {
                String errorBody = response.getBody() != null ? response.getBody().toString() : "No error body";
                throw new RuntimeException("OpenAI API call failed with status: " + response.getStatusCode() + " and body: " + errorBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to communicate with OpenAI API: " + e.getMessage(), e);
        }
    }
    public String askGeneral(String prompt, String role) {
        return ask(prompt, DEFAULT_CHAT_MODEL, role);
    }
}