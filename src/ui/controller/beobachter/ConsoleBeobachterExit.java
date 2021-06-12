package ui.controller.beobachter;

public class ConsoleBeobachterExit implements Beobachter{
    private ConsoleSubjekt consoleSubjekt;
    public ConsoleBeobachterExit(ConsoleSubjekt cs){
        this.consoleSubjekt = cs;
        this.consoleSubjekt.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        if(!this.consoleSubjekt.message.equalsIgnoreCase("add") &&
                !this.consoleSubjekt.message.equalsIgnoreCase("exit") &&
                !this.consoleSubjekt.message.equalsIgnoreCase("remove") &&
                !this.consoleSubjekt.message.equalsIgnoreCase("help") &&
                !this.consoleSubjekt.message.equalsIgnoreCase("show")){
            System.out.println("wrong input. please type \"help\" for input options");
        }
        if(this.consoleSubjekt.message.equalsIgnoreCase("exit"))
            System.exit(0);
    }
}
