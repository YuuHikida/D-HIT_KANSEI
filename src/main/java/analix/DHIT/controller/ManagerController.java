package analix.DHIT.controller;

import analix.DHIT.input.ReportSearchInput;
import analix.DHIT.model.Report;
import analix.DHIT.model.Task;
import analix.DHIT.model.User;
import analix.DHIT.service.ReportService;
import analix.DHIT.service.TaskService;
import analix.DHIT.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
//@RequestMapping("/manager")
public class ManagerController {

    private final UserService userService;
    private final ReportService reportService;
    private final TaskService taskService;

    //springが自動でインスタンスを作るのを利用して
    //UserServiceを提供しているのか？
    public ManagerController(UserService userservice, ReportService reportService, TaskService taskService) {
        this.userService = userservice;
        this.reportService = reportService;
        this.taskService = taskService;
    }

    @GetMapping("/manager/report-search")
    //↑エンドポイント　　　　　　　　　　/manager/report-search?employeeCode=1　　
    public String displayReportSearch(@RequestParam(name = "employeeCode", required = true) int employeeCode, Model model) {
        User user = userService.getUserByEmployeeCode(employeeCode);

        // コントローラ内でリクエスト属性を設定
        model.addAttribute("memberName", user.getName());
        model.addAttribute("employeeCode", user.getEmployeeCode());
        model.addAttribute("reportSearchInput", new ReportSearchInput());

        return "manager/report-search";
    }

    @PostMapping("/manager/search-report")
    public String searchReport(ReportSearchInput reportSearchInput, RedirectAttributes redirectAttributes) {
        int report_id = reportService.searchId(reportSearchInput.getEmployeeCode(), reportSearchInput.getDate());
        redirectAttributes.addAttribute("report_id", report_id);
        //DBにあるデータを参考に個人詳細ページのデータを渡す
        return "redirect:/manager/reports/{report_id}";
    }

    @GetMapping("/manager/reports/{report_id}")
    //PathVariableはpathを受け取ってくる
    public String displayReportDetail(@PathVariable("report_id") int reportId, Model model) {

        Report report = reportService.getReportById(reportId);
        List<Task> tasks = taskService.getTasksByReportId(reportId);
        User user = userService.getUserByEmployeeCode(report.getEmployeeCode());

        System.out.println(tasks.size());

        model.addAttribute("tasks", tasks);
        model.addAttribute("date", report.getDate());
        model.addAttribute("impressions", report.getImpressions());
        model.addAttribute("startTime",report.getStartTime());
        model.addAttribute("endTime",report.getEndTime());
        model.addAttribute("tomorrowSchedule",report.getTomorrowSchedule());
        //名前を入れる
        model.addAttribute("memberName",user.getName());
        model.addAttribute("isBehindTime",report.getBehideTime());
        model.addAttribute("behideReason",report.getBehideReason());
        model.addAttribute("isLeavingEarly",report.getLeavingEarly());

        return "manager/report-detail";
    }

}
