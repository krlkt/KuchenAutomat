package controller.events;

import geschaeftslogik.automat.*;
import geschaeftslogik.persistence.JBP;
import geschaeftslogik.persistence.JOS;
import jdk.nashorn.internal.scripts.JO;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class InputEventListenerImpl implements InputEventListener {
    Automat automat;

    public InputEventListenerImpl(Automat automat) {
        this.automat = automat;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        String[] str = event.getText().split(" ");

        if (automat.getMode() == Mode.Add) {
            if (str.length == 1) {
                if (automat.addHersteller(str[0])) {
                    System.out.println("Successfully added manufacturer " + str[0] + " into machine");
                } else {
                    System.out.println("error occured. Hersteller was not added into the machine");
                }
            } else {
                if (str.length == 7 || str.length == 8) {
                    Collection<Allergen> allergene = new LinkedList<>();
                    BigDecimal preis = BigDecimal.valueOf(0.0);
                    int naehrwert = 0;
                    Duration durationDays = Duration.ofDays(0);
                    if (str[0].equalsIgnoreCase("kremkuchen") || str[0].equalsIgnoreCase("obstkuchen")
                            || str[0].equalsIgnoreCase("obsttorte")) {
                        try {
                            String prs = str[2].replace(',', '.');
                            preis = BigDecimal.valueOf(Double.parseDouble(prs));
                        } catch (Exception e) {
                            System.out.println("invalid input. Third input should be price of cake");
                        }
                        try {
                            naehrwert = Integer.parseInt(str[3]);
                        } catch (Exception e) {
                            System.out.println("Cannot parse forth input into naehrwert");
                        }
                        try {
                            int days = Integer.parseInt(str[4]);
                            durationDays = Duration.ofDays(days);
                        } catch (Exception e) {
                            System.out.println("Cannot parse fifth input into duration days");
                        }
                        try {
                            if(!str[5].equalsIgnoreCase(",")) {
                                String[] allergen = str[5].split(",", 4);
                                for (int i = 0; i < allergen.length; i++) {
                                    if (allergen[i].equalsIgnoreCase("gluten")) {
                                        allergene.add(Allergen.Gluten);
                                    } else if (allergen[i].equalsIgnoreCase("sesamsamen")) {
                                        allergene.add(Allergen.Sesamsamen);
                                    } else if (allergen[i].equalsIgnoreCase("erdnuss")) {
                                        allergene.add(Allergen.Erdnuss);
                                    } else if (allergen[i].equalsIgnoreCase("haselnuss")) {
                                        allergene.add(Allergen.Haselnuss);
                                    } else {
                                        System.out.println("failed to read alergies");
                                        return;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("failed to read alergies");
                        }
                        if (str[0].equalsIgnoreCase("kremkuchen")){
                            if (str.length == 7) {
                                Kremkuchen creamCake = new KremkuchenImpl(str[6], new HerstellerImpl(str[1]));
                                createCake_HelpMethode(creamCake, allergene, preis, naehrwert, durationDays, "cream cake");
                            }else {
                                System.out.println("invalid input length");
                            }
                        }else if(str[0].equalsIgnoreCase("obstkuchen")){
                            if(str.length == 7) {
                                Obstkuchen fruitCake = new ObstkuchenImpl(str[6], new HerstellerImpl(str[1]));
                                createCake_HelpMethode(fruitCake, allergene, preis, naehrwert, durationDays, "fruit cake");
                            }else {
                                System.out.println("invalid input length");
                            }
                        }else if(str[0].equalsIgnoreCase("obsttorte")){
                            if(str.length == 8){
                                Obsttorte obsttorte = new ObsttorteImpl(str[6], str[7], new HerstellerImpl(str[1]));
                                createCake_HelpMethode(obsttorte, allergene, preis, naehrwert, durationDays, "fruit tart");
                            }else {
                                System.out.println("invalid input length");
                            }
                        }
                    } else {
                        System.out.println("Invalid cake type. Please try again.");
                    }
                } else {
                    System.out.println("invalid input length");
                }
            }
        }
        else if(automat.getMode()==Mode.Show){
            if(event.getText().equalsIgnoreCase("hersteller")){
                System.out.println(Arrays.toString(automat.showHerstellerList()));
            }
            else if(str[0].equalsIgnoreCase("kuchen")){
                if(str.length == 1) {
                    System.out.println(automat.showKuchenList());
                }else{
                    System.out.println(automat.showKuchenList(str[1]));
                }
            }
            else if(str[0].equalsIgnoreCase("allergene")){
                if(str.length>1){
                    if(str[1].equalsIgnoreCase("i")){
                        System.out.println(automat.showAllergene());
                    }else if(str[1].equalsIgnoreCase("e")) {
                        System.out.println(automat.showNotIncludedAllergene());
                    }else {
                        System.out.println("Invalid command. After allergene type i for included allergies and e for not included");
                    }
                }else {
                    System.out.println("Invalid input length. After allergene type i for included allergies and e for not included");
                }
            }else {
                System.out.println("Invalid command");
            }
        }else if(automat.getMode()==Mode.Delete){
            if(str.length == 1){
                if(str[0].matches("[0-9]+")){
                    try {
                        automat.eraseKuchen(Integer.parseInt(str[0]));
                        System.out.println("successfully erased cake on locker " + str[0]);
                    } catch (Exception e) {
                        System.out.println("failed to erase cake from locker "+ str[0]);
                    }
                }else {
                    if(automat.removeHersteller(str[0])){
                        System.out.println("successfully removed hersteller " + str[0] + " from machine");
                    }else {
                        System.out.println("failed to remove hersteller "+ str[0] + " from machine");
                    }
                }
            }else {
                System.out.println("Invalid input");
            }
        }else if(automat.getMode()==Mode.Change){
            if(str.length == 1){
                if(str[0].matches("[0-9]+")) {
                    int fachnummer = Integer.parseInt(str[0]);
                    if(automat.getFaecher(fachnummer)!=null) {
                        automat.getKuchen(fachnummer).setInspektionsdatum(new Date());
                        System.out.println("updated date of inspection");
                    }else{
                        System.out.println("fachnummer " + fachnummer + " is empty..");
                    }
                }else {
                    System.out.println("invalid input. type in locker number to update date of inspection");
                }
            }else{
                System.out.println("Invalid input");
            }
        }else if(automat.getMode()==Mode.Persistent) {
            if (event.getText().equalsIgnoreCase("savejos")) {
                JOS jos = new JOS();
                jos.save(automat);
            } else if (event.getText().equalsIgnoreCase("loadjos")) {
                JOS jos = new JOS();
                jos.load();
            } else if (event.getText().equalsIgnoreCase("savejbp")) {
                JBP jbp = new JBP();
                try {
                    jbp.save(automat);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (event.getText().equalsIgnoreCase("loadjbp")) {
                JBP jbp = new JBP();
                jbp.load();
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    void createCake_HelpMethode(Verkaufskuchen kuchen, Collection<Allergen> allergene, BigDecimal preis, int naehrwert, Duration durationDays, String name){
        kuchen.setAllergene(allergene);
        System.out.println("set cake allergene to "+ allergene);
        kuchen.setPreis(preis);
        System.out.println("set cake price to " + preis);
        kuchen.setNaehrwert(naehrwert);
        System.out.println("set naehrwert to " + naehrwert + " kcal");
        kuchen.setHaltbarkeit(durationDays);
        System.out.println("set haltbarkeit to " + durationDays.toDays() + " days");
        try {
            if(automat.addKuchen(kuchen, name)){ System.out.println("successfully added cream cake to the machine"); }
        } catch (Exception e) {
            System.out.println("failed to add cake to machine");;
        }
    }
}
