package cloud.lohmann.java.HaushaltsplanBackend.Domain.DTO;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.TaskType;
import lombok.Data;

@Data
public class HousekeepingTaskDTO {
    String name;
    Boolean finished;
    TaskType interval;

}
