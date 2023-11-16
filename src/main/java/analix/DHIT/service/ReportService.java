package analix.DHIT.service;

import analix.DHIT.exception.ReportNotFoundException;
import analix.DHIT.input.ReportCreateInput;
import analix.DHIT.mapper.ReportMapper;
import analix.DHIT.model.Report;
import analix.DHIT.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class ReportService {
    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;

    public ReportService(ReportMapper reportMapper, ReportRepository reportRepository) {
        this.reportMapper = reportMapper;
        this.reportRepository = reportRepository;
    }

    public String searchId(int employeeCode, LocalDate date) {
        return reportMapper.selectIdByEmployeeCodeAndDate(employeeCode, date);
    }

    public Report getReportById(int reportId) {
        Report report = reportRepository.findById(reportId);
        if (report == null) {
            throw new ReportNotFoundException("Report Not Found");
        }
        return report;
    }

    public String getBeforeIdById(int reportId) {
        return reportMapper.selectBeforeIdById(reportId);
    }

    public String getAfterIdById(int reportId) {
        return reportMapper.selectAfterIdById(reportId);
    }

    public String getLatestIdByEmployeeCode(int employeeCode) {
        return reportMapper.selectLatestIdByEmployeeCode(employeeCode);
    }

    public int create(
            int employeeCode,
            String condition,
            String impressions,
            String tomorrowSchedule,
            LocalDate date,
            LocalTime endTime,
            LocalTime startTime,
            boolean isLateness,
            String latenessReason,
            boolean isLeftEarly
    ) {

        Report newReport = new Report();
        newReport.setEmployeeCode(employeeCode);
        newReport.setCondition(condition);
        newReport.setImpressions(impressions);
        newReport.setTomorrowSchedule(tomorrowSchedule);
        newReport.setDate(date);

        newReport.setEndTime(adjustTime(endTime));
        newReport.setStartTime(adjustTime(startTime));

        newReport.setIsLateness(isLateness);
        newReport.setLatenessReason(latenessReason);
        newReport.setIsLeftEarly(isLeftEarly);

        this.reportRepository.save(newReport);

        return newReport.getId();

    }

    public boolean existsReport(int employeeCode, LocalDate date) {
        int count = reportMapper.countReportByEmployeeCodeAndDate(employeeCode, date);
        return count > 0;
    }

    private LocalTime adjustTime(LocalTime time) {
        // 15分単位で切り捨てする
        LocalTime truncatedTime = time.truncatedTo(ChronoUnit.MINUTES);
        return truncatedTime.minusMinutes(truncatedTime.getMinute() % 15);
    }

}
