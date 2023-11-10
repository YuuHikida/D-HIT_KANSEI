package analix.DHIT.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportTaskLink {

    @Select("SELECT task_id FROM  report_task_link WHERE report_id = #{reportId}")
    List<Integer> selectAllTaskIdByReportId(int reportId);
}
