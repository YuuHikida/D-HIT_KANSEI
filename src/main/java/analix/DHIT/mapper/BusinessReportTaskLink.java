package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BusinessReportTaskLink {

    @Select("SELECT task_id FROM  business_report_task_link WHERE business_report_id = #{reportId}")
    List<Integer> selectAllTaskIdByReportId(int reportId);
}
