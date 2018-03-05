package ch.loewenfels.raspberrybuildnotifier;

import java.util.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.loewenfels.raspberrybuildnotifier.serverpoller.GoogleAppEnginePoller;
import ch.loewenfels.raspberrybuildnotifier.serverpoller.Poller;

import humannotifier.AudioNotifier;
import humannotifier.ConsoleNotifier;

public class EchoTask extends TimerTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoTask.class);
    private final Poller poller;

    public EchoTask() {
        poller = new GoogleAppEnginePoller();
        poller.addObserver(new ConsoleNotifier());
        poller.addObserver(new AudioNotifier());
        LOGGER.info("Amount of observers: {}", poller.countObservers());
    }

    @Override
    public void run() {
        LOGGER.info(" running ... {}", new Date());
        poller.pollAndNotify();
    }
}