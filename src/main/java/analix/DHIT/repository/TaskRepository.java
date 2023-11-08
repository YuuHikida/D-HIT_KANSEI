package analix.DHIT.repository;

import analix.DHIT.model.Task;
import analix.DHIT.model.User;

import java.util.List;

public interface TaskRepository {
    List<Task> findByIds(List<Integer> taskIds);
}

