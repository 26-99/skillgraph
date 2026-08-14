// Companies
MERGE (company1:Company {name: 'Google'})
MERGE (company2:Company {name: 'Microsoft'})
MERGE (company3:Company {name: 'Amazon'});

// Jobs
MERGE (job1:Job {title: 'Java Developer'})
MERGE (job2:Job {title: 'Python Developer'})
MERGE (job3:Job {title: 'Cloud Engineer'});

// Skills
MERGE (skill1:Skill {name: 'Java'})
MERGE (skill2:Skill {name: 'Python'})
MERGE (skill3:Skill {name: 'Spring Boot'})
MERGE (skill4:Skill {name: 'AWS'})
MERGE (skill5:Skill {name: 'SQL'});

// Company → Job
MERGE (company1)-[:OFFERS]->(job1)
MERGE (company2)-[:OFFERS]->(job2)
MERGE (company3)-[:OFFERS]->(job3)

// Job → Skill
MERGE (job1)-[:REQUIRES]->(skill1)
MERGE (job1)-[:REQUIRES]->(skill3)
MERGE (job1)-[:REQUIRES]->(skill5)

MERGE (job2)-[:REQUIRES]->(skill2)
MERGE (job2)-[:REQUIRES]->(skill5)

MERGE (job3)-[:REQUIRES]->(skill4)
MERGE (job3)-[:REQUIRES]->(skill5)

// Skill → Related Skill
MERGE (skill1)-[:RELATED_TO]->(skill3)
MERGE (skill2)-[:RELATED_TO]->(skill5)
MERGE (skill3)-[:RELATED_TO]->(skill5)
MERGE (skill4)-[:RELATED_TO]->(skill5)