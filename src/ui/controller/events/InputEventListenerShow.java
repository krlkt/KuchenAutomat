package ui.controller.events;

public class InputEventListenerShow implements InputEventListener{
    ConsoleReader cr;

    @Override
    public void onInputEvent(InputEvent event) {
        if(event.getText() != null && event.getText().equalsIgnoreCase("showHersteller")){
            cr.getAutomat().showHerstellerList();
        }
    }
}
