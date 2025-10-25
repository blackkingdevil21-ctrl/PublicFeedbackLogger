package com.feedback.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class MLClassifierService {
    
    @Value("${ml.service.url}")
    private String mlServiceUrl;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public String classifyText(String text) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text", text);
            
            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
            
            ResponseEntity<Map> response = restTemplate.postForEntity(
                mlServiceUrl, 
                request, 
                Map.class
            );
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return (String) response.getBody().get("domain");
            }
        } catch (Exception e) {
            System.err.println("ML Service error: " + e.getMessage());
        }
        
        return classifyByKeywords(text);
    }
    
    private String classifyByKeywords(String text) {
        String lowerText = text.toLowerCase();
        
        if (lowerText.contains("water") || lowerText.contains("leak") || 
            lowerText.contains("supply") || lowerText.contains("tap")) {
            return "Water";
        } else if (lowerText.contains("electricity") || lowerText.contains("power") || 
                   lowerText.contains("light") || lowerText.contains("outage")) {
            return "Electricity";
        } else if (lowerText.contains("road") || lowerText.contains("pothole") || 
                   lowerText.contains("street") || lowerText.contains("highway")) {
            return "Roads";
        } else if (lowerText.contains("garbage") || lowerText.contains("waste") || 
                   lowerText.contains("trash") || lowerText.contains("sanitation")) {
            return "Sanitation";
        } else if (lowerText.contains("drainage") || lowerText.contains("sewage") || 
                   lowerText.contains("manhole")) {
            return "Drainage";
        } else {
            return "General";
        }
    }
}
