package com.example.part3.controller;

import com.example.part3.dto.LocationDTO;
import com.example.part3.service.LocationService;
import com.example.part3.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {
    private final LocationService locationService;
    private final ResourceService resourceService;

    public LocationsController(LocationService locService, ResourceService resService) {
        this.locationService = locService;
        this.resourceService = resService;
    }

    @PostMapping
    public ResponseEntity<LocationDTO> create(@Valid @RequestBody LocationDTO dto) {
        LocationDTO saved = locationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<LocationDTO> getAll() { return locationService.getAll(); }

    @GetMapping("/{id}")
    public LocationDTO getById(@PathVariable Long id) { return locationService.getById(id); }

    @PutMapping("/{id}")
    public LocationDTO update(@PathVariable Long id, @Valid @RequestBody LocationDTO dto) {
        return locationService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (resourceService.inUseByLocation(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Location " + id + " is in use by existing resources.");
        }
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

