package ui.controller.beobachter;

import geschaeftslogik.automat.Automat;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleSubjekt implements Subjekt{
    public Automat automat = new Automat(100);
    Scanner s = new Scanner(System.in);
    private List<Beobachter> bList = new LinkedList<>();
    @Override
    public void meldeAn(Beobachter b) {
        this.bList.add(b);
    }

    @Override
    public void meldeAb(Beobachter b) {
        this.bList.remove(b);
    }

    @Override
    public void benachrichtige() {
        for(Beobachter beobachter :bList){
            beobachter.aktualisiere();
        }
    }

    public String message;
    public void start(){
            do {
                System.out.println("Enter text: ");
                this.benachrichtige();
            }while (true);
    }
}
