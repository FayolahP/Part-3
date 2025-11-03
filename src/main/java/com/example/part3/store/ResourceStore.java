package com.example.part3.store;

import com.example.part3.dto.ResourceDTO;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ResourceStore {
    private final Map<Long, ResourceDTO> resources = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(0);

    public List<ResourceDTO> findAll() { return new ArrayList<>(resources.values()); }
    public ResourceDTO findById(Long id) { return resources.get(id); }
    public ResourceDTO save(ResourceDTO dto) {
        Long id = dto.id() != null ? dto.id() : idGen.incrementAndGet();
        ResourceDTO saved = new ResourceDTO(id, dto.name(), dto.locationId(), dto.categoryId());
        resources.put(id, saved);
        return saved;
    }
    public void delete(Long id) { resources.remove(id); }
    public boolean existsById(Long id) { return resources.containsKey(id); }
    public boolean existsByLocation(Long locId) {
        return resources.values().stream().anyMatch(r -> r.locationId().equals(locId));
    }
    public boolean existsByCategory(Long catId) {
        return resources.values().stream().anyMatch(r -> r.categoryId().equals(catId));
    }
}


