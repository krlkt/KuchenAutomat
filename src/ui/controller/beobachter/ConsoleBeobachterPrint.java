package ui.controller.beobachter;

public class ConsoleBeobachterPrint implements Beobachter{
    private ConsoleSubjekt consoleSubjekt;
    public ConsoleBeobachterPrint(ConsoleSubjekt cs){
        this.consoleSubjekt = cs;
        this.consoleSubjekt.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        if (this.consoleSubjekt.message != null) {
            System.out.println("Input: " + this.consoleSubjekt.message);
        }
    }
}
