package com.example.myth.mythBuster.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myth.mythBuster.model.Myth;
import com.example.myth.mythBuster.service.MythService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/myths")
public class MythController {
	
	 @Autowired
	    private MythService mythService;

	    @PostMapping("/ask")
	    public ResponseEntity<Myth> askMyth(@RequestBody Myth mythRequest) {
	        Myth response = mythService.verifyMyth(mythRequest.getMythText());
	        return ResponseEntity.ok(response);
	    }

	    @GetMapping("/history")
	    public List<Myth> getAllMyths() {
	        return mythService.getAllMyths();
	    }

}
