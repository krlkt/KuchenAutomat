package ui.controller.beobachter;

public class FastVollBeobachter implements Beobachter{
    private ConsoleSubjekt consoleSubjekt;
    public FastVollBeobachter(ConsoleSubjekt cs){
        this.consoleSubjekt = cs;
        this.consoleSubjekt.meldeAn(this);
    }

    @Override
    public void aktualisiere() {

    }
}
