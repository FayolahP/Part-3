ppackage com.example.part3.store;

import com.example.part3.dto.LocationDTO;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class LocationStore {
    private final Map<Long, LocationDTO> locations = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(0);

    public List<LocationDTO> findAll() { return new ArrayList<>(locations.values()); }
    public LocationDTO findById(Long id) { return locations.get(id); }
    public LocationDTO save(LocationDTO dto) {
        Long id = dto.id() != null ? dto.id() : idGen.incrementAndGet();
        LocationDTO saved = new LocationDTO(id, dto.building(), dto.room());
        locations.put(id, saved);
        return saved;
    }
    public void delete(Long id) { locations.remove(id); }
    public boolean existsById(Long id) { return locations.containsKey(id); }
}
