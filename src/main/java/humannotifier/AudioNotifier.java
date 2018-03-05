package humannotifier;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class AudioNotifier extends HumanNotifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudioNotifier.class);

    @Override
    protected void notifyHumanBeing(final BuildInformationDto dto) {
        switch (dto.getJobStatus()) {
        case FAILURE:
            final String wavName = "computer-information.wav";
            final ClassLoader classLoader = getClass().getClassLoader();
            final File wavFile = new File(classLoader.getResource(wavName).getFile());
            try {
                if (dto.getJobStatus().equals(BuildInformationDto.JobStatus.FAILURE)) {
                    final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "aplay " + wavFile.getAbsolutePath());
                    processBuilder.start();
                }
            } catch (final IOException e) {
                LOGGER.error("IO Error occurred on exec process: {}", e);
            }
            break;
        case SUCCESS:
            break;
        case ERROR:
            LOGGER.error("Job Status Error");
            final String errorNofierName = "errorNotifier.wav";
            final ClassLoader classLoaderError = getClass().getClassLoader();
            final File errorNofierFile = new File(classLoaderError.getResource(errorNofierName).getFile());
            try {
                if (dto.getJobStatus().equals(BuildInformationDto.JobStatus.ERROR)) {
                    final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "aplay " + errorNofierFile.getAbsolutePath());
                    processBuilder.start();
                }
            } catch (final IOException e) {
                LOGGER.error("IO Error occurred on exec process: {}", e);
            }
            break;
        }
    }
}
