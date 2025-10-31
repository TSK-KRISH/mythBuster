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

    /**
     * Verify myth and optionally attach to a user.
     * userId may be null.
     */
    public Myth verifyMyth(String mythText, Long userId) {
        String prompt = "Fact check this myth: " + mythText + ". Reply with True/False and a short explanation.";

        String aiResponse = openAIClient.getAIResponse(prompt);

        Myth myth = new Myth();
        myth.setMythText(mythText);
        myth.setAiResponse(aiResponse);
        myth.setCreatedAt(LocalDateTime.now());

        // Extract verdict (simple parse logic)
        myth.setVerdict(aiResponse != null && aiResponse.toLowerCase().contains("false") ? "False" : "True");

        myth.setUserId(userId);

        return mythRepo.save(myth);
    }

    // Backwards-compatible method if needed
    public Myth verifyMyth(String mythText) {
        return verifyMyth(mythText, null);
    }

    public List<Myth> getAllMyths() {
        return mythRepo.findAll();
    }

    public List<Myth> getMythsByUser(Long userId) {
        return mythRepo.findByUserId(userId);
    }
}
