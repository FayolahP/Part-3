package com.example.Part3.service;

import com.example.Part3.dto.ResourceDTO;
import com.example.Part3.store.InMemoryResourceStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourcesService {

    private final InMemoryResourceStore store;

    public ResourcesService(InMemoryResourceStore store) {
        this.store = store;
    }

    public List<ResourceDTO> getAll(Optional<String> category, Optional<String> q) {
        return store.findByFilters(category, q);
    }

    public Optional<ResourceDTO> getById(String id) {
        return store.findById(id);
    }
}
