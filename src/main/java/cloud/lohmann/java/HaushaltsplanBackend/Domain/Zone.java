package cloud.lohmann.java.HaushaltsplanBackend.Domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Zone {
    @Id
    Long id;
    String name;
    @OneToMany(fetch = FetchType.EAGER)
    List<HousekeepingTask> tasks;
    Boolean active;
}
