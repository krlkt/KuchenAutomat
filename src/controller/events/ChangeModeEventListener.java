package controller.events;

import geschaeftslogik.automat.Automat;

import java.util.EventListener;

public interface ChangeModeEventListener extends EventListener {
    void onChangeModeEvent(ChangeModeEvent event, Automat automat);
}
