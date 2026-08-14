package com.example.skillgraph.controller;
import com.example.skillgraph.service.SkillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/api/jobs")
    public List<Map<String, Object>> findJobsBySkill(
            @RequestParam String skill) {
        return skillService.findJobsBySkill(skill);
    }

    @GetMapping("/api/related-skills")
    public List<Map<String, Object>> findRelatedSkills(
            @RequestParam String skill) {
        return skillService.findRelatedSkills(skill);
    }

    @GetMapping("/api/multi-hop")
    public List<Map<String, Object>> findMultiHopResults(
            @RequestParam String skill) {
        return skillService.findMultiHopResults(skill);
    }
}
