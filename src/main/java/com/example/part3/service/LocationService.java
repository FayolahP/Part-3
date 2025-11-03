package com.example.part3.service;

import com.example.part3.dto.LocationDTO;
import com.example.part3.exception.NotFoundException;
import com.example.part3.store.LocationStore;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {
    private final LocationStore store = new LocationStore();

    public List<LocationDTO> getAll() { return store.findAll(); }
    public LocationDTO getById(Long id) {
        LocationDTO loc = store.findById(id);
        if (loc == null) throw new NotFoundException("Location " + id + " not found");
        return loc;
    }
    public LocationDTO create(LocationDTO dto) { return store.save(dto); }
    public LocationDTO update(Long id, LocationDTO dto) {
        getById(id);
        return store.save(new LocationDTO(id, dto.building(), dto.room()));
    }
    public void delete(Long id) { store.delete(id); }
    public boolean exists(Long id) { return store.existsById(id); }
}

