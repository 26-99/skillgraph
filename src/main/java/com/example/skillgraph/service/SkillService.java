package com.example.skillgraph.service;
import com.example.skillgraph.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Map<String, Object>> findJobsBySkill(String skill) {
        return skillRepository.findJobsBySkill(skill);
    }

    public List<Map<String, Object>> findRelatedSkills(String skill) {
        return skillRepository.findRelatedSkills(skill);
    }

    public List<Map<String, Object>> findMultiHopResults(String skill) {
        return skillRepository.findMultiHopResults(skill);
    }
}

