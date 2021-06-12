package ui.controller.beobachter;

public class ConsoleBeobachterHelp implements Beobachter{
    private ConsoleSubjekt consoleSubjekt;
    public ConsoleBeobachterHelp(ConsoleSubjekt cs){
        this.consoleSubjekt = cs;
        this.consoleSubjekt.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        if (this.consoleSubjekt.message.equals("help")){
            System.out.println("type \"add\" to enter add mode");
            System.out.println("type \"show\" to show lists");
            System.out.println("type \"exit\" to exit the program");
            System.out.println("type \"remove\" to enter remove mode");
        }
    }
}
