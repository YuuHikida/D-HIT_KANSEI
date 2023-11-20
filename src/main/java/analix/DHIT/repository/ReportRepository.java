package analix.DHIT.repository;

import analix.DHIT.model.Report;

public interface ReportRepository {
    Report findById(int reportId);
    void save(Report report);

    void deleteById(int reportId);
    void update(Report report);
}
