package cloud.lohmann.java.HaushaltsplanBackend.Repository;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.HousekeepingTask;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.TaskType;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface HousekeepingTaskRepository extends CrudRepository<HousekeepingTask, Long> {
    List<HousekeepingTask> getAllByType(TaskType type);
    Optional<HousekeepingTask> findByName(String Name);
}
