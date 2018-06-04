package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

import humannotifier.ExceptionNotifier;

public abstract class Poller extends Observable {
    protected abstract List<Optional<BuildInformationDto>> pollLatestBuildState();

    private static final Logger LOGGER = LoggerFactory.getLogger(Poller.class);

    public void pollAndNotify() {
        final List<Optional<BuildInformationDto>> buildState = pollLatestBuildState();
        for (final Optional<BuildInformationDto> latestBuildState : buildState) {
            latestBuildState.ifPresent(dto -> {
                setChanged();
                notifyObservers(dto);
                try {
                    Thread.sleep(30000);
                } catch (final Exception e) {
                    LOGGER.error("Thread sleep Exception {}", e);
                }
            });
            if (!latestBuildState.isPresent()) {
                new ExceptionNotifier().startErrorAudio();
            }
        }
    }
}
