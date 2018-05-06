package cloud.lohmann.java.HaushaltsplanBackend.Controller;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.DTO.HousekeepingTaskDTO;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.Zone;
import cloud.lohmann.java.HaushaltsplanBackend.Service.RunningTaskService;
import cloud.lohmann.java.HaushaltsplanBackend.Service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HousekeepingController {
    private final RunningTaskService runningTaskService;
    private final ZoneService zoneService;

    @GetMapping
    public List<HousekeepingTaskDTO> getActiveTasks() {
       return runningTaskService.getRunningTasksDTO();
    }

    @GetMapping("/finish/{name}")
    public void finishTask(@PathVariable String name) {
        runningTaskService.finishTask(name);
    }

    @GetMapping("/restart/{name}")
    public void restartTask(@PathVariable String name) {
        runningTaskService.restartTask(name);
    }

    @GetMapping("/zone")
    public Zone getActvieZone() {
        return zoneService.getCurrentZone();
    }

    @GetMapping("/zone/next")
    public Zone getNextZone() {
        return zoneService.getNextZone();
    }
}
