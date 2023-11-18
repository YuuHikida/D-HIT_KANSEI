package analix.DHIT.controller;

import analix.DHIT.model.User;
import analix.DHIT.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<User> userList = userService.getEmployeeInfo();
        model.addAttribute("userList", userList);
        return "/manager/employeeList";
    }
}
