import controller.events.*;
import geschaeftslogik.automat.Automat;

public class CLI_Events {
    private static int arg = 100;

    public static void main(String[] args) {
        if(args.length == 1) {
            try {
                arg = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.exit(1);
                System.out.println("invalid argument. It has to represent number of lockers for the machine");
            }
        }
        Automat automat = new Automat(arg);
        ConsoleReader cr = new ConsoleReader();

        //Handler
        ChangeModeEventHandler cmHandler = new ChangeModeEventHandler();
        InputEventHandler handler = new InputEventHandler();
        //Listener
        ChangeModeEventListener cmListener = new ChangeModeEventListenerImpl(automat);
        cmHandler.add(cmListener);
        InputEventListener inputEventListener = new InputEventListenerImpl(automat);
        handler.add(inputEventListener);

        //start
        cr.setChangeModeEventHandler(cmHandler);
        cr.setInputEventHandler(handler);
        cr.start();
    }
}
