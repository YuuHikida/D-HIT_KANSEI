package analix.DHIT.repository;

import analix.DHIT.mapper.ReportMapper;
import analix.DHIT.model.Report;

public class MysqlReportRepository implements ReportRepository{
    private final ReportMapper reportMapper;

    public MysqlReportRepository(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }
    @Override
    public Report findById(int reportId)
    {
        return this.reportMapper.SelectById(reportId);
    }
}
