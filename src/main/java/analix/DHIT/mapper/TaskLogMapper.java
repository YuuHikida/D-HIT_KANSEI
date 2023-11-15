package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import analix.DHIT.model.TaskLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskLogMapper {
    @Select("SELECT * FROM task_log WHERE report_id = #{reportId}")
    List<TaskLog> selectByReportId(int reportId);

    @Insert("INSERT INTO task_log(report_id, name, progress_rate) " +
            "VALUES(#{reportId}, #{name}, #{progressRate})")
    void insertTaskLog(TaskLog taskLog);
}
