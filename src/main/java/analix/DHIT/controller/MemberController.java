package analix.DHIT.controller;


import analix.DHIT.input.ReportCreateInput;
import analix.DHIT.model.Report;
import analix.DHIT.model.TaskLog;
import analix.DHIT.service.ReportService;
import analix.DHIT.service.TaskLogService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final TaskLogService taskLogService;
    private final ReportService reportService;

    public MemberController(TaskLogService taskLogService, ReportService reportService){
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
}
