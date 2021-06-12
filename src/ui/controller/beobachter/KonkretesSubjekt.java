package ui.controller.beobachter;

import java.util.LinkedList;
import java.util.List;

public class KonkretesSubjekt implements Subjekt{
    private List<Beobachter> beobachterList = new LinkedList<Beobachter>();

    @Override
    public void meldeAn(Beobachter b) {
        this.beobachterList.add(b);
    }

    @Override
    public void meldeAb(Beobachter b) {
        this.beobachterList.remove(b);
    }

    @Override
    public void benachrichtige() {
        for(Beobachter b: beobachterList){
            b.aktualisiere();
        }
    }

    private int zustand;
    public int gibZustand(){return zustand;}
    public void setzeZustand(int zustand){
        this.zustand = zustand;
        benachrichtige();
    }

}
