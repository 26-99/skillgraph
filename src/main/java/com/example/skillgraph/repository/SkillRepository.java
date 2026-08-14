package com.example.skillgraph.repository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository

public class SkillRepository {

    private final Driver driver;

    public SkillRepository(Driver driver) {
        this.driver = driver;
    }

    public List<Map<String, Object>> findJobsBySkill(String skill) {

        String cypher = """
                MATCH (j:Job)-[:REQUIRES]->(s:Skill)
                WHERE s.name = $skill
                RETURN j.title AS job, s.name AS skill
                ORDER BY j.title
                """;

        try (Session session = driver.session(SessionConfig.forDatabase("cognodb"))) {

            return session.executeRead(tx -> {
                List<Map<String, Object>> results = new ArrayList<>();
                var result = tx.run(cypher, Map.of("skill", skill));

                while (result.hasNext()) {
                    Record record = result.next();
                    results.add(record.asMap());
                }

                return results;
            });
        }
    }

    public List<Map<String, Object>> findRelatedSkills(String skill) {
        String cypher = """
                MATCH (s:Skill)-[:RELATED_TO]->(related:Skill)
                WHERE s.name = $skill
                RETURN s.name AS skill, related.name AS relatedSkill
                ORDER BY related.name
                """;

        try (Session session = driver.session(SessionConfig.forDatabase("cognodb"))) {

            return session.executeRead(tx -> {
                List<Map<String, Object>> results = new ArrayList<>();

                var result = tx.run(cypher, Map.of("skill", skill));

                while (result.hasNext()) {
                    Record record = result.next();
                    results.add(record.asMap());
                }

                return results;
            });
        }
    }
    public List<Map<String, Object>> findMultiHopResults(String skill) {

        String cypher = """
                MATCH (j:Job)-[:REQUIRES]->(s:Skill)-[:RELATED_TO]->(related:Skill)
                WHERE s.name = $skill
                RETURN j.title AS job,
                       s.name AS requiredSkill,
                       related.name AS relatedSkill
                ORDER BY j.title, related.name
                """;

        try (Session session = driver.session(SessionConfig.forDatabase("cognodb"))) {

            return session.executeRead(tx -> {
                List<Map<String, Object>> results = new ArrayList<>();

                var result = tx.run(cypher, Map.of("skill", skill));

                while (result.hasNext()) {
                    Record record = result.next();
                    results.add(record.asMap());
                }
                return results;
            });
        }
    }
}


