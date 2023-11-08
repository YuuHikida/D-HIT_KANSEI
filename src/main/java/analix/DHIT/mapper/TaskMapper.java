package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import analix.DHIT.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT * FROM  task WHERE id in (#{taskIds})")
    List<Task> selectTasksByIds(@Param("taskIds") List<Integer> taskIds);
}

