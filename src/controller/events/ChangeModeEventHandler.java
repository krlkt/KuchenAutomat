package controller.events;

import geschaeftslogik.automat.Automat;

import java.util.LinkedList;
import java.util.List;

public class ChangeModeEventHandler {
    private List<ChangeModeEventListener> listenerList = new LinkedList<>();
    public void add(ChangeModeEventListener listener) {
        this.listenerList.add(listener);
    }
    public void remove(ChangeModeEventListener listener) {
        this.listenerList.remove(listener);
    }
    public void handle(ChangeModeEvent event, Automat automat){for (ChangeModeEventListener listener : listenerList) listener.onChangeModeEvent(event, automat);}
}
