package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.Optional;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class DummyPoller extends Poller {
    @Override
    protected Optional<BuildInformationDto> pollLatestBuildState() {
        //        final BuildInformationDto buildInformationDto = new BuildInformationDto();
        //        buildInformationDto.setJobName("Dummy-Job");
        //        buildInformationDto.setJobStatus(JobStatus.FAILURE);
        //        buildInformationDto.setJobTime(LocalDateTime.now());
        //        return Optional.of(buildInformationDto);
        return null;
    }
}
