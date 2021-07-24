package controller.events;

import model.geschaeftslogik.automat.Automat;

import java.util.Scanner;

public class Console {
    private Automat automat;
    public Console(Automat automat){
        this.automat = automat;
    }

    //Events
    InputEventHandler inputEventHandler;
    ChangeModeEventHandler changeModeEventHandler;
    public void setInputEventHandler(InputEventHandler inputEventHandler) { this.inputEventHandler = inputEventHandler; }
    public void setChangeModeEventHandler(ChangeModeEventHandler changeModeEventHandler) { this.changeModeEventHandler = changeModeEventHandler; }

    public void start(){
        try(Scanner s=new Scanner(System.in)){
            do {
                System.out.println("Enter command:");
                String text=s.nextLine();
                if(!text.isEmpty()) {
                    Character c = text.charAt(0);
                    if (c.equals(':')) {
                        ChangeModeEvent e = new ChangeModeEvent(this, text);
                        if (null != this.changeModeEventHandler) changeModeEventHandler.handle(e);
                    } else {
                        InputEvent e = new InputEvent(this, text);
                        if (null != this.inputEventHandler) inputEventHandler.handle(e);
                    }
                    automat.benachrichtige();
                }else {
                    System.out.println("Input can't be empty");
                }
            }while (true);
        }
    }

    public Automat getAutomat() {
        return automat;
    }
}
