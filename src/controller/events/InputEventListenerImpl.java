package controller.events;

import geschaeftslogik.automat.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

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
