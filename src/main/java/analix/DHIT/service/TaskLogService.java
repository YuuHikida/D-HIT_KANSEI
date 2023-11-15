package analix.DHIT.service;

import analix.DHIT.model.TaskLog;
import analix.DHIT.repository.TaskLogRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLogService {
    private final TaskLogRepository taskLogRepository;

    public TaskLogService(
            TaskLogRepository taskLogRepository
    ) {
        this.taskLogRepository = taskLogRepository;
    }
    public List<TaskLog> getTaskLogsByReportId(int reportId)
    {
        return this.taskLogRepository.selectByReportId(reportId);
    }

    public void create(TaskLog taskLog){
        this.taskLogRepository.save(taskLog);
    }
}
