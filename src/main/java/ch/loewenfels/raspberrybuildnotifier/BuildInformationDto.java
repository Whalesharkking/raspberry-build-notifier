package ch.loewenfels.raspberrybuildnotifier;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildInformationDto {
    public enum JobStatus {
            SUCCESS, FAILURE, ERROR
    }

    private String jobName;
    private JobStatus jobStatus;
    private Date jobTime;

    public BuildInformationDto(final String jobName, final JobStatus jobStatus, final Date jobTime) {
        this.jobName = jobName;
        this.jobStatus = jobStatus;
        this.jobTime = jobTime;
    }

    public BuildInformationDto() {
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(final JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getJobTime() {
        return jobTime;
    }

    public void setJobTime(final Date jobTime) {
        this.jobTime = jobTime;
    }

    @Override
    public String toString() {
        return "JobName: " + getJobName() + "\tJobStatus: " + getJobStatus() + "\tJobTime: " + getFormattedDate(this);
    }

    private static String getFormattedDate(final BuildInformationDto buildInformationDto) {
        final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyy HH:mm:ss");
        return format.format(buildInformationDto.getJobTime());
    }
}
