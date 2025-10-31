package com.example.myth.mythBuster.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.myth.mythBuster.model.Myth;
import com.example.myth.mythBuster.service.MythService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/myths")
public class MythController {
    
    @Autowired
    private MythService mythService;

    /**
     * Accepts a Myth JSON. Expected fields:
     * - mythText (required)
     * - userId (optional)
     */
    @PostMapping("/ask")
    public ResponseEntity<Myth> askMyth(@RequestBody Myth mythRequest) {
        Long userId = mythRequest.getUserId(); // may be null
        Myth response = mythService.verifyMyth(mythRequest.getMythText(), userId);
        return ResponseEntity.ok(response);
    }

    // Existing endpoint (returns all myths) - keep for backward compatibility
    @GetMapping("/history")
    public List<Myth> getAllMyths() {
        return mythService.getAllMyths();
    }

    // New: get history for a specific user
    @GetMapping("/history/{userId}")
    public List<Myth> getUserMyths(@PathVariable Long userId) {
        return mythService.getMythsByUser(userId);
    }

}
