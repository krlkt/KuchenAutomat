package ui.controller.events;

import java.util.Scanner;

public class InputEventListenerAddHersteller implements InputEventListener {

    @Override
    public void onInputEvent(InputEvent event) {
        //ConsoleReader cr;

        if(event.getText() != null && event.getText().equalsIgnoreCase("addHersteller")) {
            System.out.println("Hersteller name to add: ");
            Scanner s = new Scanner(System.in);
            String s1 = s.next();
            //cr.getAutomat().addHersteller(s1);
        }
    }
}
