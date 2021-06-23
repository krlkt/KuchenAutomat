import controller.events.*;

public class CLI_Events {
    private static int arg = 100;
    public int getArg() { return arg; } //TODO

    public static void main(String[] args) {
        if(args.length == 1) {
            try {
                arg = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("invalid argument. It has to represent number of lockers for the machine");
            }
        }
        ConsoleReader cr = new ConsoleReader();
        //Handler
        ChangeModeEventHandler cmHandler = new ChangeModeEventHandler();
        InputEventHandler handler = new InputEventHandler();
        //Listener
        ChangeModeEventListener cmListener = new ChangeModeEventListenerImpl();
        cmHandler.add(cmListener);

        //start
        cr.setChangeModeEventHandler(cmHandler);
        cr.setInputEventHandler(handler);
        cr.start();
    }
}
