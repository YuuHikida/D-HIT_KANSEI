package analix.DHIT.repository;

import analix.DHIT.mapper.UserMapper;
import analix.DHIT.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository

public class MysqlUserRepository implements UserRepository {

    private final UserMapper userMapper;

    public MysqlUserRepository(UserMapper userMapper)
    {
      this.userMapper=userMapper;
    }
    @Override
    public User findByEmployeeCode(int employeeCode)
    {
        return this.userMapper.findByEmployeeCode(employeeCode);
    }

}
