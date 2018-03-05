package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.Observable;
import java.util.Optional;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

import humannotifier.ExceptionNotifier;

public abstract class Poller extends Observable {
    protected abstract Optional<BuildInformationDto> pollLatestBuildState();

    public void pollAndNotify() {
        final Optional<BuildInformationDto> latestBuildState = pollLatestBuildState();
        latestBuildState.ifPresent(dto -> {
            setChanged();
            notifyObservers(dto);
        });
        if (!latestBuildState.isPresent()) {
            new ExceptionNotifier().startErrorAudio();
        }
    }
}
