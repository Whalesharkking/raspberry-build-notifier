package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.List;
import java.util.Optional;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class GoogleAppEnginePoller extends Poller {
    @Override
    protected List<Optional<BuildInformationDto>> pollLatestBuildState() {
        final JsonParser parser = new JsonParser();
        return parser.get();
    }
}
