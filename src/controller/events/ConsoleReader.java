package controller.events;

import java.util.Scanner;

public class ConsoleReader {
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
                }else {
                    System.out.println("Input can't be empty");
                }
            }while (true);
        }
    }
}
