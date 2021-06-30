import controller.events.*;
import geschaeftslogik.automat.Automat;

public class CLI {
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

        InputEventListener inputEventListenerAdd = new InputEventListenerAdd(automat);
        InputEventListener inputEventListenerChange = new InputEventListenerChange(automat);
        InputEventListener inputEventListenerDelete = new InputEventListenerDelete(automat);
        InputEventListener inputEventListenerPersistent = new InputEventListenerPersistent(automat);
        InputEventListener inputEventListenerShow = new InputEventListenerShow(automat);
        handler.add(inputEventListenerAdd);
        handler.add(inputEventListenerChange);
        handler.add(inputEventListenerDelete);
        handler.add(inputEventListenerPersistent);
        handler.add(inputEventListenerShow);

        //start
        cr.setChangeModeEventHandler(cmHandler);
        cr.setInputEventHandler(handler);
        cr.start();
    }
}
