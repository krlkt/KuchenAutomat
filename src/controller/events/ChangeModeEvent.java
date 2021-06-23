package controller.events;

import java.util.EventObject;

public class ChangeModeEvent extends EventObject {
    String text;
    public ChangeModeEvent(Object source, String text) {
        super(source);
        this.text = text;
    }
    public String getText() { return text; }
}
