package analix.DHIT.repository;

import analix.DHIT.mapper.ReportMapper;
import analix.DHIT.model.Report;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlReportRepository implements ReportRepository {
    private final ReportMapper reportMapper;

    public MysqlReportRepository(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    public Report findById(int reportId) {
        return this.reportMapper.SelectById(reportId);
    }

    @Override
    public void save(Report report) {
        this.reportMapper.insertReport(report);
    }

    @Override
    public void deleteById(int reportId) {
        this.reportMapper.deleteById(reportId);
    }
}
