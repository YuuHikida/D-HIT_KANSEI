package analix.DHIT.service;

import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.input.UserCreateInput;
import analix.DHIT.mapper.UserMapper;
import analix.DHIT.model.User;
import analix.DHIT.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
//    public UserService(UserRepository userRepository) {
//            this.userRepository = userRepository;
//        }
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

    //ユーザー情報 新規登録
    public void createEmployeeInformation(UserCreateInput userCreateInput) {
        //アイコンの変換
        String filePath = userCreateInput.getIcon();
        File file = new File(filePath);
        byte[] fileContent = new byte[0];
        try {
            // ファイルをバイト配列に読み込む
            fileContent = FileUtils.readFileToByteArray(file);
            System.out.println("アイコン変換成功");
        } catch
        (IOException e) {
            e.printStackTrace();
        }
        userCreateInput.setIcon(Base64.getEncoder().encodeToString(fileContent));
        //↓MapperのクエリへUserCreateInputへ
        this.userMapper.insertEmployeeInformation(userCreateInput);
    }

//    //sha256をstring型へ
//    private String bytesToHex(byte[] bytes) {
//        StringBuilder result = new StringBuilder();
//        for (byte b : bytes) {
//            result.append(String.format("%02x", b));
//        }
//        return result.toString();
//    }

    //passwordを256処理
    public UserCreateInput encodePasswordSha256(UserCreateInput userCreateInput) {
        String test=PasswordEncoderService.convertPassword(userCreateInput);
        System.out.println(test);
        System.out.println(test.length());
        userCreateInput.setPassword(test);
//        userCreateInput.setPassword(passwordEncoder.encode(userCreateInput.getPassword()));
//        System.out.println(userCreateInput.getPassword().length());
        return userCreateInput;
    }

//        User user = new User();
//        user.setName(userCreate.getName());
//        user.setEmployeeCode(userCreate.getEmployeeCode());
//        user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
//        user.setRole(userCreate.getRole());
//        String filePath = userCreate.getIcon();
//        // String型のファイルパスを使用してFileオブジェクトを生成
//        File file = new File(filePath);
//        byte[] fileContent;
//        try {
//            // ファイルをバイト配列に読み込む
//            fileContent = FileUtils.readFileToByteArray(file);
//            // ここで fileContent を使用して必要な処理を行います
//            System.out.println("File successfully read to byte array!");
//        } catch
//        (IOException e) {
//            e.printStackTrace();
//        }
//        user.setIcon(Base64.getEncoder().encodeToString(fileContent));
//
//
//        userRepository.save(user);
//
//
//    }
//
}
