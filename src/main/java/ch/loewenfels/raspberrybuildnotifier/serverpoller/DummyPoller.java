package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.Date;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;
import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto.JobStatus;

public class DummyPoller extends Poller {
    @Override
    protected BuildInformationDto pollLatestBuildState() {
        final BuildInformationDto buildInformationDto = new BuildInformationDto();
        buildInformationDto.setJobName("Dummy-Job");
        buildInformationDto.setJobStatus(JobStatus.FAILURE);
        buildInformationDto.setJobTime(new Date());
        return buildInformationDto;
    }
}
