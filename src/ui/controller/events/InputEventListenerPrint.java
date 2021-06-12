package ui.controller.events;

public class InputEventListenerPrint implements InputEventListener{
    @Override
    public void onInputEvent(InputEvent event) {
        if(event.getText()!=null)
            System.out.println("Input: "+ event.getText());
    }
}
