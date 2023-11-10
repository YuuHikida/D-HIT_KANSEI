package analix.DHIT.repository;

import analix.DHIT.model.User;

import java.util.List;

public interface UserRepository {

    //コードの保守性
    User findByEmployeeCode(int employeeCode);

    List<User> selectAllMember();
}
