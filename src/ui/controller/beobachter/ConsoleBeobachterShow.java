package ui.controller.beobachter;

import java.util.Arrays;

public class ConsoleBeobachterShow implements Beobachter{
    private ConsoleSubjekt consoleSubjekt;
    public ConsoleBeobachterShow(ConsoleSubjekt cs){
        this.consoleSubjekt = cs;
        this.consoleSubjekt.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        if(this.consoleSubjekt.message.equalsIgnoreCase("show")){
            System.out.println("please type in the list you want to show: ");
            System.out.println("options: \"hersteller\", \"cake\"");
            String s1 = consoleSubjekt.s.next();
            if(s1.equalsIgnoreCase("hersteller")){
                System.out.println(Arrays.toString(consoleSubjekt.automat.showHerstellerList()));
            }
            else if(s1.equalsIgnoreCase("cake")){
                System.out.println(consoleSubjekt.automat.showKuchenList());
            }
        }
    }
}
