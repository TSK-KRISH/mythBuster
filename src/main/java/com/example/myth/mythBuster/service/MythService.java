package com.example.myth.mythBuster.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.myth.mythBuster.config.OpenAIClient;
import com.example.myth.mythBuster.model.Myth;
import com.example.myth.mythBuster.repository.MythRepository;

@Service
public class MythService {
	@Autowired
	private MythRepository mythRepo;

	@Autowired
	private OpenAIClient openAIClient;

	public Myth verifyMyth(String mythText) {
		String prompt = "Fact check this myth: " + mythText + ". Reply with True/False and a short explanation.";

		String aiResponse = openAIClient.getAIResponse(prompt);

		Myth myth = new Myth();
		myth.setMythText(mythText);
		myth.setAiResponse(aiResponse);
		myth.setCreatedAt(LocalDateTime.now());

		// Extract verdict (simple parse logic)
		myth.setVerdict(aiResponse.toLowerCase().contains("false") ? "False" : "True");

		return mythRepo.save(myth);
	}

	public List<Myth> getAllMyths() {
		return mythRepo.findAll();
	}
}
