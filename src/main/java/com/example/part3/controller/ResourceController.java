package com.example.part3.controller;

import com.example.part3.dto.ResourceDTO;
import com.example.part3.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResourceDTO> create(@Valid @RequestBody ResourceDTO dto) {
        ResourceDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<ResourceDTO> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResourceDTO getById(@PathVariable Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    public ResourceDTO update(@PathVariable Long id, @Valid @RequestBody ResourceDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
