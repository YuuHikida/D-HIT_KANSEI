//package analix.DHIT.service;
//
//import analix.DHIT.input.UserCreateInput;
//import org.springframework.stereotype.Service;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Base64;
//
//@Service
//public class ImageToBase64ConverterService {
//    public static String imageToBase64(String imagePath) {
//        try {
//            Path path = Paths.get(imagePath);
//            byte[] imageBytes = Files.readAllBytes(path);
//            return Base64.getEncoder().encodeToString(imageBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public void converter(UserCreateInput userCreateInput) {
//        String imagePath = userCreateInput.getIcon();
//        String base64Image = imageToBase64(imagePath);
//    }
//}
//
