package analix.DHIT.service;

import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.input.MemberSearchInput;
import analix.DHIT.model.User;
import analix.DHIT.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmployeeCode(int employeeCode) {
        User user = this.userRepository.selectByEmployeeCode(employeeCode);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }
        return user;
    }

    public List<User> getAllMember() {
        return this.userRepository.selectAllMember();
    }

    public List<User> getMemberBySearchCharacters(String searchCharacters) {
        return this.userRepository.selectMemberBySearchCharacters(searchCharacters);
    }

}
