package analix.DHIT.repository;

import analix.DHIT.model.Report;

public interface ReportRepository {
    Report findById(int reportId);
}
