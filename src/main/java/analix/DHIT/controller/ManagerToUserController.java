package analix.DHIT.controller;

import analix.DHIT.model.User;
import analix.DHIT.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                                    Model model){
        System.out.println("来てるか？");
        model.addAttribute("name",name);
        model.addAttribute("employeeCode",employeeCode);
        return "/manager/employeeList-deleteUser";
    }
}
