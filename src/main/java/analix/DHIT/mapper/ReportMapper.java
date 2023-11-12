package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface ReportMapper {
    @Select("SELECT id FROM report WHERE employee_code = #{employeeCode} and date= #{date}")
    String selectIdByEmployeeCodeAndDate(int employeeCode, LocalDate date);

    @Select("SELECT * FROM  report WHERE id = #{reportId}")
    Report SelectById(int reportId);
}
