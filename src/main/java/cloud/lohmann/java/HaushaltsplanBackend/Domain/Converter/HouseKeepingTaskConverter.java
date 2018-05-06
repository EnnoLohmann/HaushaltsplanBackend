package cloud.lohmann.java.HaushaltsplanBackend.Domain.Converter;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.DTO.HousekeepingTaskDTO;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.RunningTask;
import org.springframework.stereotype.Service;

@Service
public class HouseKeepingTaskConverter {

    public HousekeepingTaskDTO carToCarDto(RunningTask runningTask) {

        HousekeepingTaskDTO housekeepingTaskDTO = new HousekeepingTaskDTO();
        housekeepingTaskDTO.setFinished(runningTask.getFinished());
        housekeepingTaskDTO.setName(runningTask.getTask().getName());
        housekeepingTaskDTO.setInterval(runningTask.getTask().getType());
        return housekeepingTaskDTO;
    }
}
