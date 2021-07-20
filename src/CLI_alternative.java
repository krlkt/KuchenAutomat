import controller.beobachter.FastVollBeobachter;
import controller.events.*;
import geschaeftslogik.automat.Automat;

public class CLI_alternative {
    //Lösch und Allergene Auflisten Funktionalitäten sind deaktiviert und nur ein Beobachter ->
    //(Meldung bei mehr als 90% gelegte kuchen im automat) ist aktiv
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

        //Console
        Console c = new Console(automat);

        //Handler
        ChangeModeEventHandler cmHandler = new ChangeModeEventHandler();
        InputEventHandler handler = new InputEventHandler();

        //Listener
        ChangeModeEventListener cmListener = new ChangeModeEventListenerImpl(automat);
        cmHandler.add(cmListener);

        InputEventListener inputEventListenerAdd = new InputEventListenerAdd(automat);
        InputEventListener inputEventListenerChange = new InputEventListenerChange(automat);
        InputEventListener inputEventListenerPersistent = new InputEventListenerPersistent(automat);
        InputEventListener inputEventListenerShow = new InputEventListenerShow(automat);
        handler.add(inputEventListenerAdd);
        handler.add(inputEventListenerChange);
        handler.add(inputEventListenerPersistent);
        handler.add(inputEventListenerShow);

        //Beobachter
        new FastVollBeobachter(c.getAutomat());

        //start
        c.setChangeModeEventHandler(cmHandler);
        c.setInputEventHandler(handler);
        c.start();
    }
}
