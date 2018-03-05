package humannotifier;

import java.util.Observable;
import java.util.Observer;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public abstract class HumanNotifier implements Observer {
    @Override
    public final void update(final Observable o, final Object arg) {
        final BuildInformationDto dto = (BuildInformationDto) arg;
        notifyHumanBeing(dto);
    }

    protected abstract void notifyHumanBeing(BuildInformationDto dto);
}
