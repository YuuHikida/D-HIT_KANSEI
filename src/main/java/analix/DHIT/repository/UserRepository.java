package analix.DHIT.repository;

import analix.DHIT.model.User;

public interface UserRepository {

    //これをinterfaceにしたのは他でEmployeeCodeを利用する為？
    User findByEmployeeCode(int employeeCode);
}
