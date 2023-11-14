package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReportMapper {
    @Select("SELECT id FROM report WHERE employee_code = #{employeeCode} and date= #{date}")
    String selectIdByEmployeeCodeAndDate(int employeeCode, LocalDate date);

    @Select("SELECT * FROM  report WHERE id = #{reportId}")
    Report SelectById(int reportId);
    @Select("SELECT id FROM report.report WHERE employee_code = (SELECT employee_code FROM report WHERE id = #{reportId}) AND date < (SELECT date FROM report WHERE id = #{reportId}) ORDER BY date DESC LIMIT 1")
    String selectBeforeIdById(int reportId);
    @Select("SELECT id FROM report.report WHERE employee_code = (SELECT employee_code FROM report WHERE id = #{reportId}) AND date > (SELECT date FROM report WHERE id = #{reportId}) ORDER BY date LIMIT 1")
    String selectAfterIdById(int reportId);
}
