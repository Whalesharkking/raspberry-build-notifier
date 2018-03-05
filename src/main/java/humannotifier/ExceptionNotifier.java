package humannotifier;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionNotifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionNotifier.class);

    public void startErrorAudio() {
        LOGGER.error("Exception");
        final String errorNofierName = "errorNotifier.wav";
        final ClassLoader classLoaderError = getClass().getClassLoader();
        final File errorNofierFile = new File(classLoaderError.getResource(errorNofierName).getFile());
        try {
            final ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "aplay " + errorNofierFile.getAbsolutePath());
            processBuilder.start();
        } catch (final IOException e) {
            LOGGER.error("IO Error occurred on exec process: {}", e);
        }
    }
}
