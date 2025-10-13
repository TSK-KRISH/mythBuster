package com.example.myth.mythBuster.config;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Component
public class OpenAIClient {

	// ⚠️ Replace this with your actual OpenAI API key or use environment variable
	private final String apiKey = "API_KEY";
	private final WebClient client = WebClient.builder()
			.baseUrl("URL")
			.build();

	public String getAIResponse(String prompt) {
		try {
			// Send request to Gemini API
			String response = client.post().uri("?key=" + apiKey).header("Content-Type", "application/json")
					.bodyValue("""
							{
							  "contents": [
							    {
							      "parts": [
							        {"text": "%s"}
							      ]
							    }
							  ]
							}
							""".formatted(prompt)).retrieve().bodyToMono(String.class).block();

			// Parse and extract only the text response
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response);

			JsonNode candidates = root.path("candidates");
			if (candidates.isArray() && candidates.size() > 0) {
				JsonNode textNode = candidates.get(0).path("content").path("parts").get(0).path("text");

				if (!textNode.isMissingNode()) {
					// Optional cleanup: remove markdown like **True**
					return textNode.asText().replaceAll("\\*\\*", "").trim();
				}
			}

			return "No valid AI response found.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error fetching response: " + e.getMessage();
		}

	}
}