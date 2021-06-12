package ui.controller.events;

public class InputEventListenerExit implements InputEventListener{
    @Override
    public void onInputEvent(InputEvent event) {
        if(event.getText() != null && event.getText().equals("exit"))
            System.exit(0);
    }
}