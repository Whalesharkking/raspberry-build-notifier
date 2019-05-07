package ch.loewenfels.raspberrybuildnotifier;

import java.time.LocalDateTime;

public class BuildInformationDto {
    public enum JobStatus {
            SUCCESS, FAILURE, ERROR
    }

    private String jobName;
    private JobStatus jobStatus;
    private LocalDateTime jobLocalDateTime;

    public BuildInformationDto(String jobName, JobStatus jobStatus, LocalDateTime jobLocalDateTime) {
        this.jobName = jobName;
        this.jobStatus = jobStatus;
        this.jobLocalDateTime = jobLocalDateTime;
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

    public LocalDateTime getJobTime() {
        return jobTimeLocal;
    }

    public void setJobLocalDateTime(LocalDateTime jobLocalDateTime) {
        this.jobLocalDateTime = jobLocalDateTime;
    }

    @Override
    public String toString() {
        return "JobName: " + getJobName() + "\tJobStatus: " + getJobStatus() + "\tJobTime: " + jobTimeLocal;
    }
}
