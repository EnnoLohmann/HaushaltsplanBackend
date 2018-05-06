package cloud.lohmann.java.HaushaltsplanBackend.Repository;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.HousekeepingTask;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.RunningTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RunningTaskRespository extends CrudRepository<RunningTask, Long> {
    List<RunningTask> findAll();
    List<RunningTask> findAllByFinished(boolean finished);
    Optional<RunningTask> findByTask(HousekeepingTask task);
}
