package ui.controller.beobachter;

public class KonkretesBeobachter implements Beobachter{
    KonkretesSubjekt konkretesSubjekt;
    int alterZustand;
    public KonkretesBeobachter(KonkretesSubjekt konkretesSubjekt){
        this.konkretesSubjekt = konkretesSubjekt;
        this.konkretesSubjekt.meldeAn(this);
        this.alterZustand = this.konkretesSubjekt.gibZustand();
    }

    @Override
    public void aktualisiere() {
        int neuerZustand = this.konkretesSubjekt.gibZustand() ;
        if(neuerZustand != alterZustand){
            System.out.println("neuer Zustand= " + neuerZustand);
            this.alterZustand = neuerZustand;
        }
    }
}
