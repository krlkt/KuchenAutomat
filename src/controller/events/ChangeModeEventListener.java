package controller.events;

import java.util.EventListener;

public interface ChangeModeEventListener extends EventListener {
    void onChangeModeEvent(ChangeModeEvent event);
}
