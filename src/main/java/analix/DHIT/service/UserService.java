package analix.DHIT.service;

import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.input.UserCreateInput;
import analix.DHIT.mapper.UserMapper;
import analix.DHIT.model.User;
import analix.DHIT.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PasswordEncoderService passWordEncoderService;


    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, PasswordEncoderService passWordEncoderService, PasswordEncoderService passWordEncoderService1) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.passWordEncoderService = passWordEncoderService1;
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

    //登録時のemployeeCode重複チェック
    public Integer checkDuplicates(int employeeCode) {
        return this.userMapper.duplicateCode(employeeCode);
    }

    //passwordをsha256処理
    public UserCreateInput encodePasswordSha256(UserCreateInput userCreateInput) {
        String test = PasswordEncoderService.convertPassword(userCreateInput);
        userCreateInput.setPassword(test);
        return userCreateInput;
    }

    //IconをBase64へ
    public Object base64Converter(UserCreateInput userCreateInput) {
        if (userCreateInput.getIcon() != null) {
            try {
                byte[] iconfileBytes = userCreateInput.getIcon().getBytes();
                String base64String = Base64.getEncoder().encodeToString(iconfileBytes);
                userCreateInput.setConvertIcon(base64String);
                return "成功";
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return "成功";
    }

    //ユーザー情報をDBへ
    public void createEmployeeInformation(UserCreateInput userCreateInput) {
        //↓MapperのクエリへUserCreateInputへ
        this.userMapper.insertEmployeeInformation(userCreateInput);
    }

    //従業員情報一覧を表示させるのに必要な情報を取得
    public List<User> getAllEmployeeInfo()
    {
        return this.userRepository.selectAllEmployeeInfomation();
    }

}
