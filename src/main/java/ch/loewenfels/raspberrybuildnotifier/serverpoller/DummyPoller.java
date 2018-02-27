package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.time.LocalDateTime;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;
import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto.JobStatus;

public class DummyPoller extends Poller {
    @Override
    protected BuildInformationDto pollLatestBuildState() {
        final BuildInformationDto buildInformationDto = new BuildInformationDto();
        buildInformationDto.jobName = "Dummy-Job";
        buildInformationDto.jobStatus = JobStatus.FAILURE;
        buildInformationDto.resultDateTime = LocalDateTime.now().toString();
        return buildInformationDto;
    }
}
