package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.time.LocalDateTime;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;
import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto.JobStatus;

public class DummyPoller extends Poller {
    @Override
    protected BuildInformationDto pollLatestBuildState() {
        final BuildInformationDto buildInformationDto = new BuildInformationDto();
        buildInformationDto.setJobName("Dummy-Job");
        buildInformationDto.setJobStatus(JobStatus.FAILURE);
        buildInformationDto.setJobTime(LocalDateTime.now());
        return buildInformationDto;
    }
}
