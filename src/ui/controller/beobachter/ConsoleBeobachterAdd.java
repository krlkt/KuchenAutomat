package ui.controller.beobachter;

import geschaeftslogik.automat.*;

public class ConsoleBeobachterAdd implements Beobachter{
    private ConsoleSubjekt consoleSubjekt;
    public ConsoleBeobachterAdd(ConsoleSubjekt cs){
        this.consoleSubjekt = cs;
        this.consoleSubjekt.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        if(this.consoleSubjekt.message.equalsIgnoreCase("add")) {
            System.out.println("please insert a type you want to add: ");
            System.out.println("options: \"hersteller\", \"cake\"");
                String s1 = consoleSubjekt.s.next();
                if(s1 != null){
                    if(s1.equalsIgnoreCase("hersteller")){
                        System.out.println("please enter the Hersteller name: ");
                        String name = consoleSubjekt.s.next();
                        consoleSubjekt.automat.addHersteller(name);
                    }
                    else if(s1.equalsIgnoreCase("cake")) {
                        System.out.println("please enter the cake type: ");
                        System.out.println("options: \"Kremkuchen\", \"Obstkuchen\"");
                        String cakeType = consoleSubjekt.s.next();
                        if (cakeType.equalsIgnoreCase("kremkuchen")) {
                            Hersteller dummyHersteller = new HerstellerImpl("dummy");
                            System.out.println("type in name of the cake: ");
                            String cakeName = consoleSubjekt.s.next();
                            System.out.println("type in kremsorte: ");
                            String kremsorte = consoleSubjekt.s.next();
                            System.out.println("type in locker NUMBER you want to put the cake into: ");
                            int fachNummer = consoleSubjekt.s.nextInt();
                            Verkaufskuchen kremkuchen = new KremkuchenImpl(kremsorte, dummyHersteller);
                            try {
                                consoleSubjekt.automat.addKuchen(kremkuchen, cakeName, fachNummer);
                                System.out.println("added successfully. you will now be sent back to input mode");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (cakeType.equalsIgnoreCase("obstkuchen")) {
                            Hersteller dummy = new HerstellerImpl("dummy");
                            System.out.println("type in name of the cake: ");
                            String cakeName = consoleSubjekt.s.next();
                            System.out.println("type in obstsorte: ");
                            String obstsorte = consoleSubjekt.s.next();
                            System.out.println("type in locker NUMBER you want to put the cake into: ");
                            int fachNummer = consoleSubjekt.s.nextInt();
                            Verkaufskuchen obstkuchen = new KremkuchenImpl(obstsorte, dummy);
                            try {
                                consoleSubjekt.automat.addKuchen(obstkuchen, cakeName, fachNummer);
                                System.out.println("added successfully. you will now be sent back to input mode");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
