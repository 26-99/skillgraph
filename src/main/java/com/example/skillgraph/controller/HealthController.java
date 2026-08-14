package com.example.skillgraph.controller;
import org.neo4j.driver.Driver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HealthController {
    private final Driver driver;

    public HealthController(Driver driver) {
        this.driver = driver;
    }

    @GetMapping("/api/health")
    public String health() {
        try {
            driver.verifyConnectivity();
            return "SkillGraph is connected to CognoDB!";
        } catch (Exception e) {
            return "CognoDB connection failed: " + e.getMessage();
        }
    }
}
