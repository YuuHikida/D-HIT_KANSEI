package analix.DHIT.controller;

import analix.DHIT.input.UserCreateInput;
import analix.DHIT.input.UserEditInput;
import analix.DHIT.input.UserEditInput;
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
    public String displayDeleteUser(@RequestParam("employeeCode") int employeeCode,
                                    @RequestParam("name") String name,
                                    RedirectAttributes attributes,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        // ログイン中のユーザー情報を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // ログイン中のユーザーのemployeeCodeを取得
        String loggedEmployeeCode = auth.getName();
        // ユーザーが削除しようとしているemployeeCodeとログイン中のemployeeCodeを比較
        if (loggedEmployeeCode.equals(String.valueOf(employeeCode))) {
//            var errorEmployeeMsg=ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ログイン中のユーザーの編集・削除は出来ません");
            redirectAttributes.addFlashAttribute("errorEmployeeMsg", "ログイン中のユーザーの編集・削除は出来ません");
            return "redirect:/manager/employeeList";
        }
        model.addAttribute("name", name);
        model.addAttribute("employeeCode", employeeCode);
        return "/manager/employeeList-deleteUser";
    }


    //削除完了画面表示及び、削除処理
    @PostMapping("employeeList-deleteComplete")
    @Transactional
    public String deletionProcess(@RequestParam("employeeCode") int employeeCode,
                                  @RequestParam("name") String name,
                                  RedirectAttributes redirectAttributes) {
        //reportテーブルのemployeeCodeに紐づいているidを全取得
        List<Integer> reportIdAllIdGet = reportService.getIdsByEmployeeCode(employeeCode);
        //task_logのreport_idを削除
        //reportテーブルのemployeeCodeに紐づいているidを全削除
        for (Integer id : reportIdAllIdGet) {
            taskLogService.deleteByReportId(id);
            reportService.deleteById(id);
        }
        //userテーブルの値を全部削除
        userService.deleteById(employeeCode);
        redirectAttributes.addFlashAttribute("deleteCompleteMSG", name + "を削除しました");
        return "redirect:/manager/employeeList";
    }

    //編集画面表示
    @GetMapping("employeeList-edit")
    public String editDisplayMenu(@RequestParam("employeeCode") int employeeCode,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedEmployeeCode = auth.getName();
        if (loggedEmployeeCode.equals(String.valueOf(employeeCode))) {
            redirectAttributes.addFlashAttribute("errorEmployeeMsg", "ログイン中のユーザーの編集・削除は出来ません");
            return "redirect:/manager/employeeList";
        }
        model.addAttribute("userEditInput", new UserEditInput());
        model.addAttribute("employeeCode", employeeCode);

        return "/manager/employeeList-edit";
    }
//
//    //編集処理
//    @PostMapping("editEmployeeComplete")
//    public String editingProcess(@ModelAttribute("userEditInput") UserEditInput userEditInput,
//                                 @RequestParam("employeeCode")int employeeCode,
//                                 RedirectAttributes redirectAttributes) {
//        try {
//            //passwordをsha256処理
//            userService.encodePasswordSha256EditVer(userEditInput);
//            userService.base64ConverterEditVer(userEditInput);
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("EncodeError", "エラーが出ました" + e.getMessage());
//            return "redirect:/manager/employeeList-edit";
//        }
//        //DBへデータ変更処理
//        userService.EditemployeeInfomation(userEditInput);
//        redirectAttributes.addFlashAttribute("editCompleteMSG","社員番号:"+employeeCode+"の情報を更新しました");
//        redirectAttributes.addAttribute("employeeCode",employeeCode);
//        return "redirect:/manager/employeeList";
//    }
    //編集画面処理
    @PostMapping("editEmployeeComplete")
    public String editingProcess(@ModelAttribute("userEditInput") UserEditInput userEditInput,
                                 @RequestParam("employeeCode") int employeeCode,
                                 RedirectAttributes redirectAttributes) {
        Exception ErrorMSG = userService.checkTest(userEditInput, employeeCode);
        if (ErrorMSG != null) {
            redirectAttributes.addFlashAttribute("EncodeError", "エラーが出ました" + ErrorMSG);
            return "redirect:/manager/employeeList-edit";
        }
        redirectAttributes.addFlashAttribute("editCompleteMSG", "社員番号:" + employeeCode + "の情報を更新しました");
        redirectAttributes.addAttribute("employeeCode", employeeCode);
        return "redirect:/manager/employeeList";
    }
}
