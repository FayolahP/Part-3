package com.example.part3.service;

import com.example.part3.dto.ResourceDTO;
import com.example.part3.exception.InvalidReferenceException;
import com.example.part3.exception.NotFoundException;
import com.example.part3.store.ResourceStore;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResourceService {
    private final ResourceStore store = new ResourceStore();
    private final LocationService locationService;
    private final CategoryService categoryService;

    public ResourceService(LocationService locService, CategoryService catService) {
        this.locationService = locService;
        this.categoryService = catService;
    }

    public List<ResourceDTO> getAll() { return store.findAll(); }

    public ResourceDTO getById(Long id) {
        ResourceDTO res = store.findById(id);
        if (res == null) throw new NotFoundException("Resource " + id + " not found");
        return res;
    }

    public ResourceDTO create(ResourceDTO dto) {
        validateRefs(dto);
        return store.save(dto);
    }

    public ResourceDTO update(Long id, ResourceDTO dto) {
        getById(id);
        validateRefs(dto);
        return store.save(new ResourceDTO(id, dto.name(), dto.locationId(), dto.categoryId()));
    }

    public void delete(Long id) { store.delete(id); }

    private void validateRefs(ResourceDTO dto) {
        if (!locationService.exists(dto.locationId()))
            throw new InvalidReferenceException("Invalid location ID " + dto.locationId());
        if (!categoryService.exists(dto.categoryId()))
            throw new InvalidReferenceException("Invalid category ID " + dto.categoryId());
    }

    public boolean inUseByLocation(Long id) { return store.existsByLocation(id); }
    public boolean inUseByCategory(Long id) { return store.existsByCategory(id); }
}
