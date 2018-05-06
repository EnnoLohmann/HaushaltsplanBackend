package cloud.lohmann.java.HaushaltsplanBackend.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RunningTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @OneToOne
    HousekeepingTask task;
    Date startedAt;
    Boolean finished = false;
}
