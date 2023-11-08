package analix.DHIT.service;

import analix.DHIT.exception.TaskNotFoundException;
import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.mapper.BusinessReportTaskLink;
import analix.DHIT.model.Task;
import analix.DHIT.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final BusinessReportTaskLink businessReportTaskLink;

    public TaskService(TaskRepository taskRepository, BusinessReportTaskLink businessReportTaskLink) {
        this.taskRepository = taskRepository;
        this.businessReportTaskLink = businessReportTaskLink;
    }
    public List<Task> getTasksByReportId(int reportId)
    {
        List<Integer> taskIds = businessReportTaskLink.selectAllTaskIdByReportId(reportId);
        List<Task> tasks = this.taskRepository.findByIds(taskIds);
        if(tasks==null)
        {
            throw new TaskNotFoundException("Task Not Found");
        }
        return tasks;
    }


}
