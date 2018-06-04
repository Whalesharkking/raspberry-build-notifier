package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

import humannotifier.ExceptionNotifier;

public abstract class Poller extends Observable {
    protected abstract List<Optional<BuildInformationDto>> pollLatestBuildState();

    public void pollAndNotify() {
        final List<Optional<BuildInformationDto>> buildState = pollLatestBuildState();
        for (final Optional<BuildInformationDto> latestBuildState : buildState) {
            latestBuildState.ifPresent(dto -> {
                setChanged();
                notifyObservers(dto);
            });
            if (!latestBuildState.isPresent()) {
                new ExceptionNotifier().startErrorAudio();
            }
        }
    }
}
