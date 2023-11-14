package analix.DHIT.controller;

import analix.DHIT.input.MemberSearchInput;
import analix.DHIT.input.ReportSearchInput;
import analix.DHIT.model.Report;
import analix.DHIT.model.Task;
import analix.DHIT.model.User;
import analix.DHIT.service.ReportService;
import analix.DHIT.service.TaskService;
import analix.DHIT.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final UserService userService;
    private final ReportService reportService;
    private final TaskService taskService;

    public ManagerController(
            UserService userservice,
            ReportService reportService,
            TaskService taskService
    ) {
        this.userService = userservice;
        this.reportService = reportService;
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public String displayHome(
            Model model,
            @RequestParam(name = "searchCharacters", required = false) String searchCharacters
    ) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年M月d日(E)", Locale.JAPANESE));
        model.addAttribute("today", today);

        if (searchCharacters==null){
            model.addAttribute("members", userService.getAllMember());
            model.addAttribute("memberSearchInput", new MemberSearchInput());
            return "manager/home";
        }

        model.addAttribute("members", model.getAttribute("members"));
        model.addAttribute("memberSearchInput", new MemberSearchInput().withSearchCharacters(searchCharacters));
        return "manager/home";
    }

    @PostMapping("/search-member")
    public String searchMember(
            MemberSearchInput memberSearchInput,
            RedirectAttributes redirectAttributes
    ) {
        List<User> members = userService.getMemberBySearchCharacters(memberSearchInput.getSearchCharacters());
        redirectAttributes.addFlashAttribute("members", members);
        redirectAttributes.addAttribute("searchCharacters", memberSearchInput.getSearchCharacters());

        return "redirect:/manager/home";
    }

    @GetMapping("/report-search")
    public String displayReportSearch(
            @RequestParam(name = "employeeCode", required = true) int employeeCode,
            Model model
    ) {
        User member = userService.getUserByEmployeeCode(employeeCode);

        model.addAttribute("member", member);
        model.addAttribute("reportSearchInput", new ReportSearchInput());
        model.addAttribute("error", model.getAttribute("error"));

        return "manager/report-search";
    }

    @PostMapping("/search-report")
    public String searchReport(
            ReportSearchInput reportSearchInput,
            RedirectAttributes redirectAttributes
    ) {
        String reportId = reportService.searchId(
                reportSearchInput.getEmployeeCode(),
                reportSearchInput.getDate()
        );

        if (reportId == null) {
            redirectAttributes.addFlashAttribute("error", "ヒットしませんでした");
            return "redirect:/manager/report-search?employeeCode=" + reportSearchInput.getEmployeeCode();
        }

        redirectAttributes.addAttribute("reportId", reportId);
        return "redirect:/manager/reports/{reportId}";
    }

    @GetMapping("/reports/{reportId}")
    public String displayReportDetail(@PathVariable("reportId") int reportId, Model model) {

        Report report = reportService.getReportById(reportId);
        List<Task> tasks = taskService.getTasksByReportId(reportId);
        User member = userService.getUserByEmployeeCode(report.getEmployeeCode());

        model.addAttribute("report", report);
        model.addAttribute("tasks", tasks);
        model.addAttribute("member", member);

        model.addAttribute("beforeReportId", reportService.getBeforeIdById(reportId));
        model.addAttribute("afterReportId", reportService.getAfterIdById(reportId));

        String date = report.getDate().format(DateTimeFormatter.ofPattern("yyyy年M月d日(E)", Locale.JAPANESE));
        model.addAttribute("date", date);

        return "manager/report-detail";
    }

}
