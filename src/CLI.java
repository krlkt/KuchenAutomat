import controller.beobachter.AllergeneBeobachter;
import controller.events.*;
import model.geschaeftslogik.automat.Automat;
import controller.beobachter.FastVollBeobachter;

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
        InputEventListener inputEventListenerDelete = new InputEventListenerDelete(automat);
        InputEventListener inputEventListenerPersistent = new InputEventListenerPersistent(automat);
        InputEventListener inputEventListenerShow = new InputEventListenerShow(automat);
        InputEventListener inputEventListenerShowAllergene = new InputEventListenerShowAllergene(automat);
        handler.add(inputEventListenerAdd);
        handler.add(inputEventListenerChange);
        handler.add(inputEventListenerDelete);
        handler.add(inputEventListenerPersistent);
        handler.add(inputEventListenerShow);
        handler.add(inputEventListenerShowAllergene);

        //Beobachter
        new FastVollBeobachter(c.getAutomat());
        new AllergeneBeobachter(c.getAutomat());

        //start
        c.setChangeModeEventHandler(cmHandler);
        c.setInputEventHandler(handler);
        c.start();
    }
}
