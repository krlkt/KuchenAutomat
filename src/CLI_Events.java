import ui.controller.events.*;

public class CLI_Events {
    public static void main(String[] args) {
        ConsoleReader cr = new ConsoleReader();
        InputEventHandler handler = new InputEventHandler();
        InputEventListener lExit = new InputEventListenerExit();
        InputEventListener lPrint = new InputEventListenerPrint();
        InputEventListener ladd = new InputEventListenerAddHersteller();
        InputEventListener lshow = new InputEventListenerShow();
        handler.add(lExit);
        handler.add(lPrint);
        handler.add(ladd);
        handler.add(lshow);
        cr.setHandler(handler);
        cr.start();
    }
}
