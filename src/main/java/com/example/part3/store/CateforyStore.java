package com.example.part3.store;

import com.example.part3.dto.CategoryDTO;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryStore {
    private final Map<Long, CategoryDTO> categories = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(0);

    public List<CategoryDTO> findAll() { return new ArrayList<>(categories.values()); }
    public CategoryDTO findById(Long id) { return categories.get(id); }
    public CategoryDTO save(CategoryDTO dto) {
        Long id = dto.id() != null ? dto.id() : idGen.incrementAndGet();
        CategoryDTO saved = new CategoryDTO(id, dto.name());
        categories.put(id, saved);
        return saved;
    }
    public void delete(Long id) { categories.remove(id); }
    public boolean existsById(Long id) { return categories.containsKey(id); }
}

