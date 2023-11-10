package analix.DHIT.service;

import analix.DHIT.mapper.ReportTaskLink;
import analix.DHIT.model.Task;
import analix.DHIT.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ReportTaskLink businessReportTaskLink;

    public TaskService(TaskRepository taskRepository, ReportTaskLink businessReportTaskLink) {
        this.taskRepository = taskRepository;
        this.businessReportTaskLink = businessReportTaskLink;
    }
    public List<Task> getTasksByReportId(int reportId)
    {
        List<Integer> taskIds = businessReportTaskLink.selectAllTaskIdByReportId(reportId);
        //System.out.println("tasklink" + taskIds.size());
        if(taskIds.isEmpty())
        {
            return new ArrayList<>();
        }
        return this.taskRepository.findByIds(taskIds);
    }


}
