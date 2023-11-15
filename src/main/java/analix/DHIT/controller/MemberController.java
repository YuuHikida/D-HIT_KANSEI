package analix.DHIT.controller;


import analix.DHIT.input.ReportCreateInput;
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

import java.time.LocalDate;
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
        List<TaskLog> taskLogs = taskLogService.getTaskLogsByReportId(Integer.parseInt(latestReportId));

        ReportCreateInput reportCreateInput = new ReportCreateInput();
        reportCreateInput.setDate(LocalDate.now());
        reportCreateInput.setTaskLogs(taskLogs);

        model.addAttribute("reportCreateInput", reportCreateInput);
        return "member/report-create";
    }

    @Transactional
    @PostMapping("/report/create")
    public String createReport(ReportCreateInput reportCreateInput){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int employeeCode = Integer.parseInt(authentication.getName());

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

        System.out.println(reportCreateInput.getTaskLogs());

        // タスクが存在するならタスクログに追加
        if (reportCreateInput.getTaskLogs() != null) {
            List<TaskLog> taskLogs = reportCreateInput.getTaskLogs();
            taskLogs.forEach(x -> x.setReportId(newReportId));
            for (TaskLog taskLog : taskLogs) {
                System.out.println("タスク追加するよ");
                taskLogService.create(taskLog);
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
