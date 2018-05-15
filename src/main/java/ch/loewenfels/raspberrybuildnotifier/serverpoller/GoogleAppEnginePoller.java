package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.Optional;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class GoogleAppEnginePoller extends Poller {
    @Override
    protected Optional<BuildInformationDto> pollLatestBuildState() {
        return new JsonParser().get();
    }
}
