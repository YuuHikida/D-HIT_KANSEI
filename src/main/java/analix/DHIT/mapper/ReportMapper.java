package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReportMapper {
    @Select("SELECT id FROM report WHERE employee_code = #{employeeCode} and date= #{date}")
    String selectIdByEmployeeCodeAndDate(int employeeCode, LocalDate date);
    @Select("SELECT * FROM report WHERE id = #{reportId}")
    Report SelectById(int reportId);
    @Select("SELECT id FROM report WHERE employee_code = (SELECT employee_code FROM report WHERE id = #{reportId}) AND date < (SELECT date FROM report WHERE id = #{reportId}) ORDER BY date DESC LIMIT 1")
    String selectBeforeIdById(int reportId);
    @Select("SELECT id FROM report WHERE employee_code = (SELECT employee_code FROM report WHERE id = #{reportId}) AND date > (SELECT date FROM report WHERE id = #{reportId}) ORDER BY date LIMIT 1")
    String selectAfterIdById(int reportId);
    @Select("SELECT id FROM report WHERE employee_code = #{employeeCode} ORDER BY date DESC LIMIT 1")
    String selectLatestIdByEmployeeCode(int employeeCode);

    @Insert("INSERT INTO report(employee_code, `condition`, impressions, tomorrow_schedule, date, start_time, end_time, is_lateness, lateness_reason, is_left_early) " +
            "VALUES(#{employeeCode}, #{condition}, #{impressions}, #{tomorrowSchedule}, #{date}, #{startTime}, #{endTime}, #{isLateness}, #{latenessReason}, #{isLeftEarly})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertReport(Report report);

}
