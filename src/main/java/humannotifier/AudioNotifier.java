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
        threadSleep();
        switch (dto.getJobStatus()) {
        case FAILURE:
            final String wavFailure = "build-failed.wav";
            threadSleep();
            new FreeTTS(dto.getJobName() + " Failed").speak();
            notifie(wavFailure, JobStatus.FAILURE);
            break;
        case SUCCESS:
            break;
        default:
            new FreeTTS(dto.getJobName() + " Have a Problem").speak();
            threadSleep();
            final String wavError = "errorNotifier.wav";
            notifie(wavError, JobStatus.ERROR);
            break;
        }
    }

    private void threadSleep() {
        try {
            Thread.sleep(1000);
        } catch (final Exception e) {
            LOGGER.error("Thread sleep Exception {}", e);
        }
    }

    private void notifie(final String wavName, final JobStatus jobStatus) {
        LOGGER.info("JobStatus: {}", jobStatus);
        final ClassLoader classLoader = getClass().getClassLoader();
        final File wavFile = new File(classLoader.getResource(wavName).getFile());
        try {
            if (buildInformationDto.getJobStatus().equals(jobStatus)) {
                final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "aplay " + wavFile.getAbsolutePath());
                processBuilder.start();
            }
        } catch (final IOException e) {
            LOGGER.error("IO Error occurred on exec process: {}", e);
        }
    }
}
