package ui.controller.events;

public class InputEvent extends java.util.EventObject{
    private String text;

    public InputEvent(Object source, String text) {
        super(source);
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
