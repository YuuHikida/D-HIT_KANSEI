package analix.DHIT.repository;

import analix.DHIT.model.TaskLog;

import java.util.List;

public interface TaskLogRepository {
    List<TaskLog> selectByReportId(int reportId);
    void save(TaskLog taskLog);
}

