package analix.DHIT.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Report {
    private int id;
    private int employeeCode;
    private String condition;
    private String impressions;
    private String tomorrowSchedule;
    private LocalDate date;
    private LocalTime endTime;
    private LocalTime startTime;
    private Boolean isLateness;
    private String latenessReason;
    private Boolean isLeftEarly;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(int employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getTomorrowSchedule() {
        return tomorrowSchedule;
    }

    public void setTomorrowSchedule(String tomorrowSchedule) {
        this.tomorrowSchedule = tomorrowSchedule;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getBehideTime() {
        return isLateness;
    }

    public void setBehideTime(Boolean behideTime) {
        isLateness = behideTime;
    }

    public String getLatenessReason() {
        return latenessReason;
    }

    public void setLatenessReason(String latenessReason) {
        this.latenessReason = latenessReason;
    }

    public Boolean getLeavingEarly() {
        return isLeftEarly;
    }

    public void setLeavingEarly(Boolean leavingEarly) {
        isLeftEarly = leavingEarly;
    }

}
