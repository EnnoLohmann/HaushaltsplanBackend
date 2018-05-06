package cloud.lohmann.java.HaushaltsplanBackend.Batch;

import cloud.lohmann.java.HaushaltsplanBackend.Service.RunningTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final RunningTaskService runningTaskService;

    @Scheduled(cron = "0 0 6 * * *")
    public void addTasksForTheDay(){
        runningTaskService.addTasksForTheDay();
    }


}
