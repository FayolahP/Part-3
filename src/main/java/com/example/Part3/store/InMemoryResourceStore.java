package com.example.Part3.store;

import com.example.Part3.dto.ResourceDTO;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryResourceStore {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryResourceStore.class);

    private final Map<String, ResourceDTO> byId = new HashMap<>();
    private final List<ResourceDTO> all = new ArrayList<>();

    public InMemoryResourceStore() {
        seedData();
        logger.info("Seeded {} resources into memory.", all.size());
    }

    private void seedData() {
        List<ResourceDTO> resources = List.of(
                new ResourceDTO("1", "Math Tutoring Center", "Tutoring", "Building A, Room 101", "https://famu.edu/tutoring", List.of("math", "help", "study")),
                new ResourceDTO("2", "Writing Lab", "Lab", "Library 2nd Floor", "https://famu.edu/writinglab", List.of("writing", "english")),
                new ResourceDTO("3", "Career Advising", "Advising", "Student Center 210", "https://famu.edu/careers", List.of("career", "resume")),
                new ResourceDTO("4", "STEM Learning Center", "Tutoring", "Science Hall 300", "https://famu.edu/stem", List.of("physics", "engineering", "math")),
                new ResourceDTO("5", "Counseling Services", "Advising", "Wellness Center", "https://famu.edu/counseling", List.of("mental health", "support"))
        );

        for (ResourceDTO r : resources) {
            byId.put(r.id(), r);
            all.add(r);
        }
    }

    public List<ResourceDTO> findAll() {
        return List.copyOf(all);
    }

    public Optional<ResourceDTO> findById(String id) {
        return Optional.ofNullable(byId.get(id));
    }

    public List<ResourceDTO> findByFilters(Optional<String> category, Optional<String> q) {
        return all.stream()
                .filter(r -> category.map(c -> r.category().equalsIgnoreCase(c)).orElse(true))
                .filter(r -> q.map(query -> {
                    String lowerQ = query.toLowerCase();
                    boolean nameMatch = r.name().toLowerCase().contains(lowerQ);
                    boolean tagMatch = r.tags().stream().anyMatch(tag -> tag.toLowerCase().contains(lowerQ));
                    return nameMatch || tagMatch;
                }).orElse(true))
                .collect(Collectors.toList());
    }
}

