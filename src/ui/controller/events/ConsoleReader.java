package ui.controller.events;

import geschaeftslogik.automat.Automat;

import java.util.Scanner;

public class ConsoleReader {
    Automat automat = new Automat(100);
    private InputEventHandler handler;
    public void setHandler(InputEventHandler handler){
        this.handler = handler;
    }

    public Automat getAutomat(){
        return this.automat;
    }

    public void start(){
        try (Scanner s = new Scanner(System.in)){
            do{
                System.out.println("Enter text: ");
                String text = s.next();
                InputEvent e = new InputEvent(this, text);
                if (this.handler != null) handler.handle(e);
            }while(true);
        }
    }
}
