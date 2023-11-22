//package analix.DHIT.logic;
//
//import java.nio.file.Path;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Paths;
//import java.util.Base64;
////import static jdk.jpackage.internal.WixAppImageFragmentBuilder.Component.File;
//
//public class IconConvertToBase64 {
//
//    public String defalutIconGet(){
//        String base64String=readBase64FromFile();
//        byte[] imageBytes = Base64.getDecoder().decode(base64String);
//        return  Base64.getEncoder().encodeToString(imageBytes);
//    }
//    private  static String readBase64FromFile(){
//        try{
//            Path path= Paths.get("src/main/resources/DefaltImageIcon");
//            byte[] iconBytes=File.readAllbytes(path);
//            return new String (iconBytes,StandardCharsets.UTF_8);
//        }catch(IOException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}
