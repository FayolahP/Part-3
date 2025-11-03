package com.example.part3.service;

import com.example.part3.dto.CategoryDTO;
import com.example.part3.exception.NotFoundException;
import com.example.part3.store.CategoryStore;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryStore store = new CategoryStore();

    public List<CategoryDTO> getAll() { return store.findAll(); }
    public CategoryDTO getById(Long id) {
        CategoryDTO cat = store.findById(id);
        if (cat == null) throw new NotFoundException("Category " + id + " not found");
        return cat;
    }
    public CategoryDTO create(CategoryDTO dto) { return store.save(dto); }
    public CategoryDTO update(Long id, CategoryDTO dto) {
        getById(id);
        return store.save(new CategoryDTO(id, dto.name()));
    }
    public void delete(Long id) { store.delete(id); }
    public boolean exists(Long id) { return store.existsById(id); }
}
