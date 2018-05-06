package cloud.lohmann.java.HaushaltsplanBackend.Repository;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.Zone;
import org.springframework.data.repository.CrudRepository;

public interface ZoneRepository extends CrudRepository<Zone, Long> {
    Zone findTopByOrderByIdDesc();
    Zone findByActive(Boolean active);
}
