package analix.DHIT.controller;


import analix.DHIT.input.ReportCreateInput;
import analix.DHIT.input.ReportSearchInput;
import analix.DHIT.model.Report;
import analix.DHIT.model.TaskLog;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final UserService userService;
    private final TaskLogService taskLogService;
    private final ReportService reportService;

    public MemberController(UserService userService, TaskLogService taskLogService, ReportService reportService){
        this.userService = userService;
        this.taskLogService = taskLogService;
        this.reportService = reportService;
    }
    @GetMapping("/report/create")
    public String displayReportCreate(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int employeeCode = Integer.parseInt(authentication.getName());
        String latestReportId = reportService.getLatestIdByEmployeeCode(employeeCode);
        ReportCreateInput reportCreateInput = new ReportCreateInput();
        reportCreateInput.setDate(LocalDate.now());

        if (latestReportId == null){
            model.addAttribute("reportCreateInput", reportCreateInput);
            return "member/report-create";
        }

        Report report = reportService.getReportById(Integer.parseInt(latestReportId));

        reportCreateInput.setStartTime(report.getStartTime());
        reportCreateInput.setEndTime(report.getEndTime());

        reportCreateInput.setTaskLogs(taskLogService.getIncompleteTaskLogsByReportId(Integer.parseInt(latestReportId)));
        model.addAttribute("reportCreateInput", reportCreateInput);
        return "member/report-create";

    }

    @Transactional
    @PostMapping("/report/create")
    public String createReport(ReportCreateInput reportCreateInput, RedirectAttributes redirectAttributes){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int employeeCode = Integer.parseInt(authentication.getName());


        if(reportService.existsReport(employeeCode,reportCreateInput.getDate())){
            redirectAttributes.addFlashAttribute("error", reportCreateInput.getDate() + "は既に業務報告書が存在しています");
            return "redirect:/member/report/create";
        }

        int newReportId = reportService.create(
                employeeCode,
                reportCreateInput.getCondition(),
                reportCreateInput.getImpressions(),
                reportCreateInput.getTomorrowSchedule(),
                reportCreateInput.getDate(),
                reportCreateInput.getEndTime(),
                reportCreateInput.getStartTime(),
                reportCreateInput.getIsLateness(),
                reportCreateInput.getLatenessReason(),
                reportCreateInput.getIsLeftEarly()
        );

        // タスクが存在するならタスクログに追加
        if (reportCreateInput.getTaskLogs() != null) {
            List<TaskLog> taskLogs = reportCreateInput.getTaskLogs();
            taskLogs.forEach(x -> x.setReportId(newReportId));
            for (TaskLog taskLog : taskLogs) {
                if (taskLog != null) {
                    taskLogService.create(taskLog);
                }
            }
        }

        return "redirect:/member/report/create-completed";
    }

    @GetMapping("/report/create-completed")
    public String displayReportCreateCompleted(
    ) {
        return "member/report-create-completed";
    }

    @GetMapping("/report-search")
    public String displayReportSearch(
            Model model
    ) {
        model.addAttribute("reportSearchInput", new ReportSearchInput());
        model.addAttribute("error", model.getAttribute("error"));

        return "member/report-search";
    }

    @PostMapping("/search-report")
    public String searchReport(
            ReportSearchInput reportSearchInput,
            RedirectAttributes redirectAttributes
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int employeeCode = Integer.parseInt(authentication.getName());

        String reportId = reportService.searchId(
                employeeCode,
                reportSearchInput.getDate()
        );

        if (reportId == null) {
            redirectAttributes.addFlashAttribute("error", "ヒットしませんでした");
            return "redirect:/member/report-search";
        }

        redirectAttributes.addAttribute("reportId", reportId);
        return "redirect:/member/reports/{reportId}";
    }

    @GetMapping("/reports/{reportId}")
    public String displayReportDetail(@PathVariable("reportId") int reportId, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int employeeCode = Integer.parseInt(authentication.getName());

        Report report = reportService.getReportById(reportId);
        if(report.getEmployeeCode() != employeeCode) {
            return "redirect:/member/report/create";
        }

        List<TaskLog> taskLogs = taskLogService.getTaskLogsByReportId(reportId);
        User member = userService.getUserByEmployeeCode(report.getEmployeeCode());

        model.addAttribute("report", report);
        model.addAttribute("taskLogs", taskLogs);
        model.addAttribute("member", member);

        model.addAttribute("beforeReportId", reportService.getBeforeIdById(reportId));
        model.addAttribute("afterReportId", reportService.getAfterIdById(reportId));

        String date = report.getDate().format(DateTimeFormatter.ofPattern("yyyy年M月d日(E)", Locale.JAPANESE));
        model.addAttribute("date", date);

        return "member/report-detail";
    }

    @PostMapping("/reports/{reportId}/delete")
    @Transactional
    public String deleteReport(
            @PathVariable int reportId
    ) {
        Report report = reportService.getReportById(reportId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int employeeCode = Integer.parseInt(authentication.getName());

        if(report.getEmployeeCode() != employeeCode) {
            return "redirect:/member/report/create";
        }

        this.taskLogService.deleteByReportId(reportId);
        this.reportService.deleteById(reportId);

        return "redirect:/member/report/delete-completed";
    }

    @GetMapping("/report/delete-completed")
    public String displayReportDeleteCompleted(
    ) {
        return "member/report-delete-completed";
    }

    @GetMapping("/reports/{reportId}/edit")
    public String displayReportEdit(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int employeeCode = Integer.parseInt(authentication.getName());
        String latestReportId = reportService.getLatestIdByEmployeeCode(employeeCode);
        ReportCreateInput reportCreateInput = new ReportCreateInput();
        reportCreateInput.setDate(LocalDate.now());

        if (latestReportId == null){
            model.addAttribute("reportCreateInput", reportCreateInput);
            return "member/report-create";
        }

        Report report = reportService.getReportById(Integer.parseInt(latestReportId));

        reportCreateInput.setStartTime(report.getStartTime());
        reportCreateInput.setEndTime(report.getEndTime());

        reportCreateInput.setTaskLogs(taskLogService.getIncompleteTaskLogsByReportId(Integer.parseInt(latestReportId)));
        model.addAttribute("reportCreateInput", reportCreateInput);
        return "member/report-create";

    }
}
