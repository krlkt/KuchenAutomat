package controller.events;

import geschaeftslogik.automat.Automat;

import java.util.EventListener;

public interface InputEventListener extends EventListener {
    void onInputEvent(InputEvent event, Automat automat);
}
