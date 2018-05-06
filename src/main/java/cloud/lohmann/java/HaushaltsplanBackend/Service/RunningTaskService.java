package cloud.lohmann.java.HaushaltsplanBackend.Service;

import cloud.lohmann.java.HaushaltsplanBackend.Domain.Converter.HouseKeepingTaskConverter;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.DTO.HousekeepingTaskDTO;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.HousekeepingTask;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.RunningTask;
import cloud.lohmann.java.HaushaltsplanBackend.Domain.TaskType;
import cloud.lohmann.java.HaushaltsplanBackend.Repository.HousekeepingTaskRepository;
import cloud.lohmann.java.HaushaltsplanBackend.Repository.RunningTaskRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RunningTaskService {
    private final HousekeepingTaskRepository housekeepingTaskRepository;
    private final ZoneService zoneService;
    private final RunningTaskRespository runningTaskRespository;
    private final HouseKeepingTaskConverter houseKeepingTaskConverter;

    public ArrayList<RunningTask> addTasksForTheDay() {
        removeFinishedTasks();

        Calendar rightNow = Calendar.getInstance();

        List<HousekeepingTask> acitveTasks = runningTaskRespository.findAll().stream().map(runningTask -> runningTask.getTask()).collect(Collectors.toList());

        ArrayList<RunningTask> newTasks = new ArrayList<>();
        newTasks.addAll(housekeepingTaskRepository.getAllByType(TaskType.DAILY).stream().filter(housekeepingTask -> !acitveTasks.contains(housekeepingTask)).map(this::TaskToRunningTask).collect(Collectors.toList()));

        if(rightNow.get(Calendar.DAY_OF_WEEK) == 6){
            newTasks.addAll(housekeepingTaskRepository.getAllByType(TaskType.WEEKLY).stream().filter(housekeepingTask -> !acitveTasks.contains(housekeepingTask)).map(this::TaskToRunningTask).collect(Collectors.toList()));
            newTasks.addAll(zoneService.getNextZone().getTasks().stream().filter(housekeepingTask -> !acitveTasks.contains(housekeepingTask)).map(this::TaskToRunningTask).collect(Collectors.toList()));

            if(rightNow.get(Calendar.WEEK_OF_YEAR) % 2 == 0){
                newTasks.addAll(housekeepingTaskRepository.getAllByType(TaskType.TWOWEEKLY).stream().filter(housekeepingTask -> !acitveTasks.contains(housekeepingTask)).map(this::TaskToRunningTask).collect(Collectors.toList()));
            }
        }

        runningTaskRespository.saveAll(newTasks);

        return newTasks;
     }

    private void removeFinishedTasks() {
        runningTaskRespository.findAllByFinished(true).forEach(runningTask -> runningTaskRespository.delete(runningTask));
    }

    public Iterable<RunningTask> getRunningTasks(){
        return runningTaskRespository.findAll();
     }

    public List<HousekeepingTaskDTO> getRunningTasksDTO(){
        return runningTaskRespository.findAll().stream().map(runningTask -> houseKeepingTaskConverter.carToCarDto(runningTask)).collect(Collectors.toList());
    }


    private RunningTask TaskToRunningTask(HousekeepingTask task){
        RunningTask runningTask = new RunningTask();
        runningTask.setTask(task);
        runningTask.setStartedAt(new Date());

        return runningTask;
    }

    public void finishTask(String name) {
        RunningTask task = runningTaskRespository.findByTask(housekeepingTaskRepository.findByName(name).get()).get();
        task.setFinished(true);
        runningTaskRespository.save(task);
    }

    public void restartTask(String name) {
        RunningTask task = runningTaskRespository.findByTask(housekeepingTaskRepository.findByName(name).get()).get();
        task.setFinished(false);
        runningTaskRespository.save(task);    }
}
