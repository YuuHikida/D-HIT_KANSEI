package analix.DHIT.controller;

import analix.DHIT.model.User;
import analix.DHIT.service.ReportService;
import analix.DHIT.service.TaskLogService;
import analix.DHIT.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerToUserController {
    private final UserService userService;
    private final ReportService reportService;
    private final TaskLogService taskLogService;
    public ManagerToUserController(UserService userService, ReportService reportService, TaskLogService taskLogService) {
        this.userService = userService;
        this.reportService = reportService;
        this.taskLogService = taskLogService;
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
    @PostMapping("employeeList-deleteComplete")
    @Transactional
    public String deletionProcess(@RequestParam("employeeCode")int employeeCode,
                                  @RequestParam("name")String name,
                                  RedirectAttributes redirectAttributes)
    {
        //reportテーブルのemployeeCodeに紐づいているidを全取得
        List<Integer> reportIdAllIdGet= reportService.getIdsByEmployeeCode(employeeCode);
        //task_logのreport_idを削除
        //reportテーブルのemployeeCodeに紐づいているidを全削除
        for (Integer id : reportIdAllIdGet) {
            taskLogService.deleteByReportId(id);
            reportService.deleteById(id);
        }
        //userテーブルの値を全部削除
        userService.deleteById(employeeCode);
        redirectAttributes.addFlashAttribute("deleteCompleteMSG",name+"を削除しました");
        return "redirect:/manager/employeeList";
    }
}
