package ch.loewenfels.raspberrybuildnotifier;

import java.time.LocalDateTime;

public class BuildInformationDto {
    public enum JobStatus {
            SUCCESS, FAILURE, ERROR
    }

    private String jobName;
    private JobStatus jobStatus;
    private LocalDateTime jobTimeLocal;

    public BuildInformationDto(final String jobName, final JobStatus jobStatus, final LocalDateTime jobTimeLocal) {
        this.jobName = jobName;
        this.jobStatus = jobStatus;
        this.jobTimeLocal = jobTimeLocal;
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

    public void setJobTime(final LocalDateTime jobTimeLocal) {
        this.jobTimeLocal = jobTimeLocal;
    }

    @Override
    public String toString() {
        return "JobName: " + getJobName() + "\tJobStatus: " + getJobStatus() + "\tJobTime: " + jobTimeLocal;
    }
}
