package humannotifier;

import org.apache.log4j.Logger;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class ConsoleNotifier extends HumanNotifier {
    private static final Logger LOGGER = Logger.getLogger(ConsoleNotifier.class);

    @Override
    protected void notifyHumanBeing(final BuildInformationDto dto) {
        LOGGER.info("Result of last poll: " + dto.toString());
    }
}
