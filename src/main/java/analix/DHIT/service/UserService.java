package analix.DHIT.service;

import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.model.User;
import analix.DHIT.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.UserRequest;



@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private final UserRepository userRepository;



    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public User getUserByEmployeeCode(int employeeCode)
    {
        User user=this.userRepository.findByEmployeeCode(employeeCode);
        if(user==null)
        {
            throw new UserNotFoundException("User Not Found");
        }
        return user;git checkout ishikawaa
    }
    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    public List<User> searchAll() {
        return userRepository.findAll();
    }



    /**
     * ユーザー情報 新規登録
     * @param user ユーザー情報
     */
    public void create(UserRequest userRequest) {
        Date now = new Date();
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmployeeCode(userRequest.getEmployeeCode());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setIcon(userRequest.getIcon());
        user.setCreateDate(now);
        user.setUpdateDate(now);
        userRepository.save(user);


    }


}
