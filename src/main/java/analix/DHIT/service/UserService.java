package analix.DHIT.service;

import analix.DHIT.exception.UserNotFoundException;
import analix.DHIT.input.UserCreate;

import analix.DHIT.input.MemberSearchInput;

import analix.DHIT.model.User;
import analix.DHIT.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;


import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {


    @Autowired
    private final UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User getUserByEmployeeCode(int employeeCode) {
        private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public User getUserByEmployeeCode ( int employeeCode){
            User user = this.userRepository.selectByEmployeeCode(employeeCode);
            if (user == null) {
                throw new UserNotFoundException("User Not Found");
            }
            return user;
        }


        /*
         * ユーザー情報 新規登録
         * @param userCreate ユーザー情報
         */
        public void create (UserCreate userCreate){

            User user = new User();
            user.setName(userCreate.getName());
            user.setEmployeeCode(userCreate.getEmployeeCode());
            user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
            user.setRole(userCreate.getRole());
            String filePath = userCreate.getIcon();
            // String型のファイルパスを使用してFileオブジェクトを生成
            File file = new File(filePath);
            byte[] fileContent;
            try {
                // ファイルをバイト配列に読み込む
                fileContent = FileUtils.readFileToByteArray(file);
                // ここで fileContent を使用して必要な処理を行います
                System.out.println("File successfully read to byte array!");
            } catch
            (IOException e) {
                e.printStackTrace();
            }
            user.setIcon(Base64.getEncoder().encodeToString(fileContent));


            userRepository.save(user);


        }


        public List<User> getAllMember () {
            return this.userRepository.selectAllMember();
        }

        public List<User> getMemberBySearchCharacters (String searchCharacters){
            return this.userRepository.selectMemberBySearchCharacters(searchCharacters);
        }

        // 全てのユーザを返すメソッド

        // ユーザーを登録するメソッド

        // ユーザーを更新するメソッド

        // ユーザーを削除するメソッド


    }

    public List<User> getAllMember() {
        return this.userRepository.selectAllMember();
    }

    public List<User> getMemberBySearchCharacters(String searchCharacters) {
        return this.userRepository.selectMemberBySearchCharacters(searchCharacters);
    }

    // 全てのユーザを返すメソッド

    // ユーザーを登録するメソッド

    // ユーザーを更新するメソッド

    // ユーザーを削除するメソッド

}
