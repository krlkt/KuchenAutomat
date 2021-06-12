package ui.controller.beobachter;

public class ConsoleBeobachterRemove implements Beobachter {
    private ConsoleSubjekt consoleSubjekt;
    public ConsoleBeobachterRemove(ConsoleSubjekt cs){
        this.consoleSubjekt = cs;
        this.consoleSubjekt.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        if(this.consoleSubjekt.message.equalsIgnoreCase("Remove")){
            System.out.println("which type do you want to remove: ");
            System.out.println("options: \"hersteller\", \"cake\"");
            String type = consoleSubjekt.s.next();
            if(type.equalsIgnoreCase("hersteller")){
                System.out.println("type in hersteller name you want to remove");
                String name = consoleSubjekt.s.next();
                consoleSubjekt.automat.removeHersteller(name);
            }
            else if(type.equalsIgnoreCase("cake")){
                System.out.println("type in locker NUMBER you want to remove cake from");
                int num = consoleSubjekt.s.nextInt();
                try {
                    consoleSubjekt.automat.eraseKuchen(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
