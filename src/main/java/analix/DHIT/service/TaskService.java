package analix.DHIT.service;

import analix.DHIT.mapper.ReportTaskLinkMapper;
import analix.DHIT.model.Task;
import analix.DHIT.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ReportTaskLinkMapper reportTaskLinkMapper;

    public TaskService(
            TaskRepository taskRepository,
            ReportTaskLinkMapper reportTaskLinkMapper
    ) {
        this.taskRepository = taskRepository;
        this.reportTaskLinkMapper = reportTaskLinkMapper;
    }
    public List<Task> getTasksByReportId(int reportId)
    {
        List<Integer> taskIds = reportTaskLinkMapper.selectAllTaskIdByReportId(reportId);
        if(taskIds.isEmpty())
        {
            return new ArrayList<>();
        }
        return this.taskRepository.findByIds(taskIds);
    }
}
