package humannotifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class ConsoleNotifier extends HumanNotifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleNotifier.class);

    @Override
    protected void notifyHumanBeing(final BuildInformationDto dto) {
        LOGGER.info("Result of last poll: {}", dto.toString());
    }
}
