package ch.loewenfels.raspberrybuildnotifier;

import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;

public class BuildInformationDto {
    public enum JobStatus {
            @SerializedName("Success")
            SUCCESS, @SerializedName("FAILURE")
            FAILURE, @SerializedName("ERROR")
            ERROR
    }

    private String jobName;
    private JobStatus jobStatus;
    private LocalDateTime jobTime;

    public BuildInformationDto(final String name, final JobStatus jobStatus, final LocalDateTime jobTime) {
        this.jobName = name;
        this.jobStatus = jobStatus;
        this.jobTime = jobTime;
    }

    public BuildInformationDto(final String name, final String jobStatus, final LocalDateTime jobTime) {
        this(name, JobStatus.valueOf(jobStatus), jobTime);
    }

    public BuildInformationDto() {
    }

    @Override
    public String toString() {
        return "[jobname: " + jobName + " jobStatus: " + jobStatus + " jobTime: " + jobTime + " ]";
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
        return jobTime;
    }

    public void setJobTime(final LocalDateTime jobTime) {
        this.jobTime = jobTime;
    }
}
