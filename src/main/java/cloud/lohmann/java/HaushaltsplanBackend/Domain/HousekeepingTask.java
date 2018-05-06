package cloud.lohmann.java.HaushaltsplanBackend.Domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class HousekeepingTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    TaskType type;

}
