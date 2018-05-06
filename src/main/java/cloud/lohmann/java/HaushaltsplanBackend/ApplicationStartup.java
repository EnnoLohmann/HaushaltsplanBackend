package cloud.lohmann.java.HaushaltsplanBackend;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.HousekeepingTask;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.TaskType;
import cloud.lohmann.java.HaushaltsplanBackend.Repository.HousekeepingTaskRepository;
import cloud.lohmann.java.HaushaltsplanBackend.Repository.ZoneRepository;
import cloud.lohmann.java.HaushaltsplanBackend.Service.RunningTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private final RunningTaskService runningTaskService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        runningTaskService.addTasksForTheDay();
    }
}
