package analix.DHIT.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Boolean isBehideTime;
    private String behideReason;
    private Boolean isLeavingEarly;

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
        return isBehideTime;
    }

    public void setBehideTime(Boolean behideTime) {
        isBehideTime = behideTime;
    }

    public String getBehideReason() {
        return behideReason;
    }

    public void setBehideReason(String behideReason) {
        this.behideReason = behideReason;
    }

    public Boolean getLeavingEarly() {
        return isLeavingEarly;
    }

    public void setLeavingEarly(Boolean leavingEarly) {
        isLeavingEarly = leavingEarly;
    }

}
