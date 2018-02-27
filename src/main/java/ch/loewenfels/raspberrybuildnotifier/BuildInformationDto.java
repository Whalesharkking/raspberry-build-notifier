package ch.loewenfels.raspberrybuildnotifier;

public class BuildInformationDto {
    public enum JobStatus {
            SUCCESS, FAILURE, ERROR
    }

    public String jobName;
    public JobStatus jobStatus;
    public String resultDateTime;

    public BuildInformationDto(final String name, final JobStatus jobStatus, final String resultDateTime) {
        this.jobName = name;
        this.jobStatus = jobStatus;
        this.resultDateTime = resultDateTime;
    }

    public BuildInformationDto(final String name, final String jobStatus, final String resultDateTime) {
        this(name, JobStatus.valueOf(jobStatus), resultDateTime);
    }

    public BuildInformationDto() {
    }

    @Override
    public String toString() {
        return "[jobname: " + jobName + " jobStatus: " + jobStatus + " resultDateTime: " + resultDateTime + " ]";
    }
}
