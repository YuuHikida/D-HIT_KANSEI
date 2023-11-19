package analix.DHIT.controller;

import analix.DHIT.model.User;
import analix.DHIT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerToUserController {
    private final UserService userService;

    public ManagerToUserController(UserService userService) {
        this.userService = userService;
    }

    //全社員情報一覧表示
    @GetMapping("/employeeList")
    public String displayEmployeeList(Model model) {
        List<User> userList = userService.getAllEmployeeInfo();
        model.addAttribute("userList", userList);
        return "/manager/employeeList";
    }
    //社員削除画面表示
    @PostMapping("employeeList-deleteUser")
    public String displayDeleteUser(@RequestParam("employeeCode")int employeeCode,
                                    @RequestParam("name")String name,
                                    RedirectAttributes attributes,
                                    RedirectAttributes redirectAttributes,
                                    Model model){
        // ログイン中のユーザー情報を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // ログイン中のユーザーのemployeeCodeを取得
        String loggedEmployeeCode = auth.getName();
        // ユーザーが削除しようとしているemployeeCodeとログイン中のemployeeCodeを比較
        if (loggedEmployeeCode.equals(String.valueOf(employeeCode))) {
//            var errorEmployeeMsg=ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ログイン中のユーザーの編集・削除は出来ません");
            redirectAttributes.addFlashAttribute("errorEmployeeMsg","ログイン中のユーザーの編集・削除は出来ません");
            return "redirect:/manager/employeeList";
            }
        model.addAttribute("name",name);
        model.addAttribute("employeeCode",employeeCode);
        return "/manager/employeeList-deleteUser";
       }


    //削除完了画面表示及び、削除処理
    @GetMapping("employeeList-deleteComplete")
    public String deletionProcess(@RequestParam("employeeCode")int employeeCode)
    {
        System.out.println("きてるかああああああああああああああああああああああああああああ");
        userService.userDelete(employeeCode);
        return "/manager/employeeList-deleteComplete";
    }
}
