package com.example.myth.mythBuster.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "myths")
public class Myth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String mythText;

    @Column(columnDefinition = "TEXT")
    private String aiResponse;

    @Column(length = 50)
    private String verdict; // e.g. "True", "False", or "Uncertain"

    private LocalDateTime createdAt = LocalDateTime.now();

    // New field to link myth to user (nullable to preserve legacy rows)
    @Column(name = "user_id", nullable = true)
    private Long userId;

    public Myth() {
    }

    public Myth(String mythText, String aiResponse, String verdict) {
        this.mythText = mythText;
        this.aiResponse = aiResponse;
        this.verdict = verdict;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMythText() {
        return mythText;
    }

    public void setMythText(String mythText) {
        this.mythText = mythText;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
