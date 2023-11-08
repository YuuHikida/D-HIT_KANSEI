package analix.DHIT.service;

import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.model.User;
import analix.DHIT.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {


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
        return user;
    }

}
