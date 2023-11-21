//package analix.DHIT.service;
//
//import analix.DHIT.input.UserEditInput;
//import analix.DHIT.model.User;
//
//public class UserEditInputValidator {
//
//    public void checkAndAssignDefaultValues(UserEditInput userEditInput) {
//
//        if (input.getName() != null) {
//            input.setName(user.getName());
//        }
//        if (input.getPassword() == null) {
//            input.setPassword(DEFAULT_PASSWORD);
//        }
//        if (input.getRole() == null) {
//            input.setRole(DEFAULT_ROLE);
//        }
//        if (input.getIcon() == null) {
//            // Set default icon or handle as needed
//            // input.setIcon(DEFAULT_ICON);
//        }
//        if (input.getConvertIcon() == null) {
//            input.setConvertIcon(DEFAULT_CONVERT_ICON);
//        }
//    }
//
//    public static void main(UserEditInput userEditInput, User user) {
//        UserEditInput input = new UserEditInput(); // ここに適切なインスタンスをセットしてください
//
//        UserEditInputValidator validator = new UserEditInputValidator();
//        validator.checkAndAssignDefaultValues(input);
//
//        // 検証後の値を出力してみましょう
//        System.out.println("EmployeeCode: " + input.getEmployeeCode());
//        System.out.println("Name: " + input.getName());
//        System.out.println("Password: " + input.getPassword());
//        System.out.println("Role: " + input.getRole());
//        System.out.println("Icon: " + input.getIcon());
//        System.out.println("ConvertIcon: " + input.getConvertIcon());
//    }
//}
//
