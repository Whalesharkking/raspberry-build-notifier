package ch.loewenfels.raspberrybuildnotifier;

import java.util.Timer;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App implements Daemon {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static Timer timer = null;

    public static void main(final String[] args) {
        BasicConfigurator.configure();
        timer = new Timer();
        timer.schedule(new EchoTask(), 0, 1000 * 60 * 5);
    }

    @Override
    public void init(final DaemonContext context) throws DaemonInitException, Exception {
        LOGGER.info("init...");
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("starting ...");
        main(null);
    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("stopping ...");
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("done.");
    }
}
