package analix.DHIT.repository;

import analix.DHIT.mapper.TaskMapper;
import analix.DHIT.model.Task;
import analix.DHIT.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MysqlTaskRepository implements TaskRepository {
    private final TaskMapper taskMapper;

    public MysqlTaskRepository(TaskMapper taskMapper)
    {
        this.taskMapper=taskMapper;
    }

    @Override
    public List<Task> findByIds(List<Integer> taskIds)
    {
        return this.taskMapper.selectTasksByIds(taskIds);
    }

}
