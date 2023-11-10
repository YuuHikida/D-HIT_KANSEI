package analix.DHIT.repository;

import analix.DHIT.model.User;

public interface UserRepository {

    //コードの保守性
    User findByEmployeeCode(int employeeCode);
}
