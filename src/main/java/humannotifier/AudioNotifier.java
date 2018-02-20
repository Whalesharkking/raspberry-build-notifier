package humannotifier;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class AudioNotifier extends HumanNotifier {
    private static final Logger LOGGER = Logger.getLogger(AudioNotifier.class);

    @Override
    protected void notifyHumanBeing(final BuildInformationDto dto) {
        switch (dto.jobStatus) {
        case FAILURE:
            final String wavName = "build-failed.wav";
            final ClassLoader classLoader = getClass().getClassLoader();
            final File wavFile = new File(classLoader.getResource(wavName).getFile());
            //final Process process = null;
            try {
                if (dto.jobStatus.equals(BuildInformationDto.JobStatus.FAILURE)) {
                    final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "aplay " + wavFile.getAbsolutePath());
                    processBuilder.start();
                }
            } catch (final IOException e) {
                LOGGER.error("IO Error occurred on exec process", e);
            }
            break;
        case SUCCESS:
            break;
        case ERROR:
            LOGGER.error("Job Status Error");
            break;
        }
    }
}
