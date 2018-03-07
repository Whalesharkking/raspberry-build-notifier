package humannotifier;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;
import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto.JobStatus;

public class AudioNotifier extends HumanNotifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudioNotifier.class);
    private BuildInformationDto buildInformationDto;

    @Override
    protected void notifyHumanBeing(final BuildInformationDto dto) {
        buildInformationDto = dto;
        switch (dto.getJobStatus()) {
        case FAILURE:
            final String wavFailure = "build-failed.wav";
            notifie(wavFailure, JobStatus.FAILURE);
            break;
        case ERROR:
            final String wavError = "build-failed.wav";
            notifie(wavError, JobStatus.ERROR);
            break;
        case SUCCESS:
            break;
        }
    }

    private void notifie(final String wavName, final JobStatus failure) {
        LOGGER.info("JobStatus: {}", failure);
        textToSpeechJobName();
        final ClassLoader classLoader = getClass().getClassLoader();
        final File wavFile = new File(classLoader.getResource(wavName).getFile());
        try {
            if (buildInformationDto.getJobStatus().equals(failure)) {
                final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "aplay " + wavFile.getAbsolutePath());
                processBuilder.start();
            }
        } catch (final IOException e) {
            LOGGER.error("IO Error occurred on exec process: {}", e);
        }
    }

    private void textToSpeechJobName() {
    }
}
