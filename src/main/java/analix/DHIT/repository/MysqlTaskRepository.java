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
        List<String> stringTaskIds = taskIds.stream().map(Object::toString).toList();
        //System.out.println("taskIds"+taskIds.size());
        for (Integer taskId : taskIds) {
            System.out.println(taskId);
        }
        return this.taskMapper.selectTasksByIds(stringTaskIds);
    }

}
