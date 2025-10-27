package com.example.Part3.controller;

import com.example.Part3.dto.ResourceDTO;
import com.example.Part3.service.ResourcesService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

    private static final Logger logger = LoggerFactory.getLogger(ResourcesController.class);

    private final ResourcesService service;

    public ResourcesController(ResourcesService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResourceDTO> getAll(
            @RequestParam Optional<String> category,
            @RequestParam Optional<String> q) {

        logger.info("GET /api/resources with filters category={} q={}", category.orElse("none"), q.orElse("none"));
        return service.getAll(category, q);
    }

    @GetMapping("/{id}")
    public ResourceDTO getById(@PathVariable String id) {
        logger.info("GET /api/resources/{}", id);
        return service.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with id " + id + " not found"));
    }

    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    private static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
