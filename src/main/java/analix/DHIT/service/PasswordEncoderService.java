package analix.DHIT.service;

import analix.DHIT.input.UserCreateInput;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncoderService {
    public static String hashStringToSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String  convertPassword(UserCreateInput userCreateInput) {
        String input = userCreateInput.getPassword();
        System.out.println(input);
        return hashStringToSHA256(input);
    }
}
