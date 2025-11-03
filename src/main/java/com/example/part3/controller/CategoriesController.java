package com.example.part3.controller;

import com.example.part3.dto.CategoryDTO;
import com.example.part3.service.CategoryService;
import com.example.part3.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    private final ResourceService resourceService;

    public CategoriesController(CategoryService catService, ResourceService resService) {
        this.categoryService = catService;
        this.resourceService = resService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) {
        CategoryDTO saved = categoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<CategoryDTO> getAll() { return categoryService.getAll(); }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable Long id) { return categoryService.getById(id); }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (resourceService.inUseByCategory(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Category " + id + " is in use by existing resources.");
        }
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

