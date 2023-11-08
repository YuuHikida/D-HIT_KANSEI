package analix.DHIT.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Report {
    private int id;
    private int employee_code;
    private String condition;
    private String impressions;
    private String tomorrowSchedule;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isBehideTime;
    private String behideReason;
    private Boolean isLeavingEarly;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(int employee_code) {
        this.employee_code = employee_code;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalDateTime endTime) {
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
