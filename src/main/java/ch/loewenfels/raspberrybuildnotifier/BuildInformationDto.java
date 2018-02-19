package ch.loewenfels.raspberrybuildnotifier;

import java.time.LocalDateTime;

public class BuildInformationDto {
    public enum JobStatus {
            SUCCESS, FAILURE, ERROR
    }

    public String name;
    public JobStatus jobStatus;
    public LocalDateTime resultDateTime;

    public BuildInformationDto(final String name, final JobStatus jobStatus, final LocalDateTime resultDateTime) {
        this.name = name;
        this.jobStatus = jobStatus;
        this.resultDateTime = resultDateTime;
    }

    public BuildInformationDto(final String name, final String jobStatus, final LocalDateTime resultDateTime) {
        this(name, JobStatus.valueOf(jobStatus), resultDateTime);
    }

    public BuildInformationDto() {
    }

    @Override
    public String toString() {
        return "[jobname: " + name + " jobStatus: " + jobStatus + " resultDateTime: " + resultDateTime + " ]";
    }
}
