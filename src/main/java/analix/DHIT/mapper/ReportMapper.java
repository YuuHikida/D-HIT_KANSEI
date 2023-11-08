package analix.DHIT.mapper;

import analix.DHIT.model.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface ReportMapper {

    @Select("SELECT id FROM business_report WHERE employee_code = #{employeeCode} and date= #{date}")
    //いったん文字列で受け取ります
    String selectIdByEmployeeCodeAndDate(int employeeCode,LocalDate date);


    @Select("SELECT * FROM  business_report WHERE id = #{reportId}")
    Report SelectById(int reportId);
}
