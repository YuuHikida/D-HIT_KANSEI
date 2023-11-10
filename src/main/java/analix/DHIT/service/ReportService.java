package analix.DHIT.service;

import analix.DHIT.exception.ReportNotFoundException;
import analix.DHIT.mapper.ReportMapper;
import analix.DHIT.model.Report;
import analix.DHIT.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDate;

@Service
public class ReportService {
    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;


    public ReportService(ReportMapper reportMapper,ReportRepository reportRepository)
    {
        this.reportMapper = reportMapper;
        this.reportRepository=reportRepository;
    }
    public String searchId(int employeeCode, LocalDate date)
    {
        return reportMapper.selectIdByEmployeeCodeAndDate(employeeCode,date);
    }
    public Report getReportById(int reportId)
    {
        Report report=reportRepository.findById(reportId);
        if(report==null)
        {
            System.out.println("koko");
            throw new ReportNotFoundException("Report Not Found");
        }
        return report;
    }

}
