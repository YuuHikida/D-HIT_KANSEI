package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import analix.DHIT.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Select("""
      <script>
        SELECT * FROM
          task
        <where> id in
        <foreach item="taskId" collection="taskIds" open="(" separator="," close=")">
              #{taskId}
        </foreach>
        </where>
      </script>
    """)
    List<Task> selectTasksByIds(@Param("taskIds") List<String> taskIds);
}

