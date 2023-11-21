package analix.DHIT.service;

import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.input.UserCreateInput;
import analix.DHIT.input.UserEditInput;
import analix.DHIT.mapper.UserMapper;
import analix.DHIT.model.User;
import analix.DHIT.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
//DBに接続するための処理を記述するところ

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

//    //passwordをsha256処理(ジェネリック型の処理)もしエラーが出たら上記のやつを復活させて
//    public <T extends UserCreateInput> T encodePasswordSha256(T userCreateInput) {
//        String test = PasswordEncoderService.convertPassword(userCreateInput);
//        userCreateInput.setPassword(test);
//        return userCreateInput;
//    }


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
    public List<User> getAllEmployeeInfo() {
        return this.userRepository.selectAllEmployeeInfomation();
    }


    //ユーザー削除
    public void deleteById(int employeeCode) {
        this.userMapper.deleteById(employeeCode);
    }

    //ユーザー編集用のsha256
    public UserEditInput encodePasswordSha256EditVer(UserEditInput userEditInput) {
        String test = PasswordEncoderEditVerService.convertPassword(userEditInput);
        userEditInput.setPassword(test);
        return userEditInput;
    }

    //ユーザー編集用のIconをBase64へ
    public Exception base64ConverterEditVer(UserEditInput userEditInput) {
        if (userEditInput.getIcon() != null) {
            try {
                System.out.println("↓これええええ");
                System.out.println(userEditInput.getIcon());
                byte[] iconfileBytes = userEditInput.getIcon().getBytes();
                String base64String = Base64.getEncoder().encodeToString(iconfileBytes);
                userEditInput.setConvertIcon(base64String);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return e;
            }
        }
        return null;
    }

    public void EditemployeeInfomation(UserEditInput userEditInput) {
        this.userMapper.editEmployeeInfomation(userEditInput);
    }

    //編集画面入力のヴァリデーションチェック
    public Exception checkTest(UserEditInput userEditInput, int employeeCode) {
        //入力されてないuserEditInputの値をuserモデルにある値を入れる
        User user = getUserByEmployeeCode(employeeCode);
        if (userEditInput.getName().isEmpty()) {
            userEditInput.setName(user.getName());
        }
        if (userEditInput.getPassword().isEmpty()) {
            userEditInput.setPassword(user.getPassword());
        } else {
            try {
                encodePasswordSha256EditVer(userEditInput);
            } catch (Exception e) {
                return e;
            }
        }
        if (userEditInput.getRole().isEmpty()) {
            userEditInput.setRole(user.getRole());
        }
        if (userEditInput.getIcon().isEmpty()) {
            userEditInput.setConvertIcon(user.getIcon());
        } else {
            try {
                base64ConverterEditVer(userEditInput);
            }catch(Exception e){
                return e;
            }
        }
        //ここで残りの値をDBに値を入れる
        EditemployeeInfomation(userEditInput);
        return null;
    }
}
