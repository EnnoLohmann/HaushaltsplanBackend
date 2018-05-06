package cloud.lohmann.java.HaushaltsplanBackend.Service;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.Zone;
import cloud.lohmann.java.HaushaltsplanBackend.Repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public Zone getNextZone(){
        Zone currentZone = zoneRepository.findByActive(true);
        Zone lastZone = zoneRepository.findTopByOrderByIdDesc();
        Optional<Zone> nextZone = null;

        if(currentZone.getId() == lastZone.getId()){
            nextZone = zoneRepository.findById(1L);
        } else {
            nextZone = zoneRepository.findById(currentZone.getId() + 1L);
        }

        currentZone.setActive(false);
        nextZone.get().setActive(true);

        zoneRepository.save(currentZone);
        zoneRepository.save(nextZone.get());

        return nextZone.get();
    }

    public Zone getCurrentZone() {
       return zoneRepository.findByActive(true);

    }

}
