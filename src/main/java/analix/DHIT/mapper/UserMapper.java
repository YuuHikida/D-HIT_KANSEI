package analix.DHIT.mapper;

import analix.DHIT.input.UserCreateInput;
import analix.DHIT.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE employee_code = #{employeeCode}")
    User findByEmployeeCode(int employeeCode);

    @Select("SELECT * FROM user where role = 'MEMBER'")
    List<User> selectAllMember();

    @Select("SELECT * FROM user WHERE name LIKE CONCAT('%', #{searchCharacters}, '%') and role = 'MEMBER'")
    List<User> selectMemberBySearchCharacters(@Param("searchCharacters") String searchCharacters);

    //employeeCodeの重複をチェック
    @Select("SELECT employee_Code FROM user WHERE employee_Code = #{employeeCode};")
    Integer duplicateCode(int employeeCode);

    //社員情報をDBへ
    @Insert("INSERT INTO USER(employee_code,name,password,role,icon)" + "VALUES(#{employeeCode},#{name},#{password},#{role},#{convertIcon})")
    void insertEmployeeInformation(UserCreateInput userCreateInput);

    @Select("SELECT * FROM user")
    List<User> selectAllEmployeeInfo();

    //削除機能の最後に使うuser削除
    @Delete("DELETE FROM user WHERE employee_code = #{employeeCode}")
    void deleteById(int employeeCode);



}
