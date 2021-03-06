package model.geschaeftslogik.automat;

import controller.beobachter.Beobachter;
import controller.beobachter.Subjekt;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class Automat implements Serializable, Subjekt {
    /**
     * Anzahl von faecher musst bei der Erstellung von Automat eingegeben werden
     */
    private int faecherAnzahl=100;
    private Mode mode = Mode.Add; //default mode: Add
    private Fach[] faecher;
    private List<Hersteller> herstellerList = new ArrayList<>();

    static final long serialVersionUID=1L;

    //Constructors
    public Automat(){}

    public Automat(int faecherAnzahl){
        this.faecherAnzahl = faecherAnzahl;
        this.faecher = new Fach[faecherAnzahl];
    }

    //Hauptmethoden
    public boolean addHersteller(String name){
        if(name.length()==0){       //name cant be empty
            return false;
        }
        Hersteller h = new HerstellerImpl(name);
        if(h.getName() != null) {
            for (Hersteller hersteller : herstellerList) {
                if (hersteller.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        herstellerList.add(h);
        return true;
    }

    public boolean addHersteller(Hersteller hersteller){
        if(hersteller.getName() != null) {
            for (Hersteller value : herstellerList) {
                if (value.getName().equalsIgnoreCase(hersteller.getName())) {
                    return false;
                }
            }
        }
        herstellerList.add(hersteller);
        return true;
    }

    public String[] showHerstellerList(){

        String[] erg = new String[herstellerList.size()];
        for(int i=0; i<herstellerList.size(); i++){
            erg[i] = herstellerList.get(i).getName() + ": " + kuchenAnzahl(herstellerList.get(i));
        }
        return erg;
    }

    int kuchenAnzahl(Hersteller hersteller){
        int count=0;
        for(int i=0; i<faecherAnzahl; i++){
            if(faecher[i]!=null){
                if(faecher[i].getKuchen().getHersteller().getName().equalsIgnoreCase(hersteller.getName())){count++;}
            }
        }
        return count;
    }

    public boolean removeHersteller(String name){
        //check ob bei Kuchen gel??schende name gibt
        for(int i=0; i<faecherAnzahl; i++){
            if (faecher[i] != null) {
                if(faecher[i].getKuchen().getHersteller().getName().equalsIgnoreCase(name)){ return false; }
            }
        }

        for(int i=0; i<herstellerList.size();i++){
                if(herstellerList.get(i).getName().equalsIgnoreCase(name)) {
                    herstellerList.remove(i);
                    return true;
                }
        }
        return false;
    }

    public boolean addKuchen(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        if(faecher[fachNummer] != null){
            throw new Exception("fach ist besetzt!");
        }
        if(fachNummer > faecherAnzahl-1){
            throw new Exception("es gibt kein Fach mit der Fachnummer "+ fachNummer);
        }

        if(herstellerExist(kuchen)) {
            Date date = new Date();
            kuchen.setInspektionsdatum(date);

            Fach fach = new FachImpl(kuchen, name);
            faecher[fachNummer] = fach;
            faecher[fachNummer].getKuchen().setFachnummer(fachNummer);
            return true;
        }
        throw new Exception("Hersteller doesn't exist");
    }

    //kuchen automatisch ins erst m??gliche freie Fach hinzuf??gen
    public boolean addKuchen(Verkaufskuchen kuchen, String name) throws Exception {
        for(int i=0; i<faecherAnzahl; i++){
            if(faecher[i]==null){
                if(herstellerExist(kuchen)) {
                    Date date = new Date();
                    kuchen.setInspektionsdatum(date);

                    Fach fach = new FachImpl(kuchen, name);
                    faecher[i] = fach;
                    faecher[i].getKuchen().setFachnummer(i);
                    return true;
                }
                throw new Exception("Hersteller doesn't exist");
            }
        }
        throw new Exception("automat ist voll");
    }

    boolean herstellerExist(Verkaufskuchen kuchen){
        for (Hersteller hersteller : herstellerList) {
            if (kuchen.getHersteller().getName().equalsIgnoreCase(hersteller.getName())) return true;
        }
        return false;
    }

    public String showKuchenList() {
        String ergebnis="";
        for(int i=0; i<faecherAnzahl;i++){
            if(faecher[i] != null){
                ergebnis += print_helpMethod(i);
            }
        }
        return ergebnis;
    }

    public String showKuchenList(String type) {
        String ergebnis="";
        switch(type){
            case "obstkuchen":
                for(int i=0; i<faecherAnzahl;i++){
                    if(faecher[i] != null && faecher[i].getKuchen() instanceof ObstkuchenImpl){
                        ergebnis += print_helpMethod(i);
                    }
                }  break;
            case "kremkuchen":
                for(int i=0; i<faecherAnzahl;i++){
                    if(faecher[i] != null && faecher[i].getKuchen() instanceof KremkuchenImpl){
                        ergebnis += print_helpMethod(i);
                    }
                } break;
            case "obsttorte":
                for(int i=0; i<faecherAnzahl;i++){
                    if(faecher[i] != null && faecher[i].getKuchen() instanceof ObsttorteImpl){
                        ergebnis += print_helpMethod(i);
                    }
                } break;
            default:
                return showKuchenList();
        }
        return ergebnis;
    }

    String print_helpMethod(int i){
        String erg;
        String fn = Integer.toString(faecher[i].getFachnummer());
        if(faecher[i].getKuchen().getHaltbarkeit()!=null) {
            erg = "Fach " + fn + ": " + faecher[i].getName() + ", Inspektions datum: " +
                    faecher[i].getKuchen().getInspektionsdatum() + ", Haltbarkeit: " + faecher[i].getKuchen().getHaltbarkeit().toHours()
                    + " Stunden" + System.lineSeparator();
        }else{
            erg = "Fach " + fn + ": " + faecher[i].getName() + ", Inspektions datum: " +
                    faecher[i].getKuchen().getInspektionsdatum() + System.lineSeparator();
        }
        return erg;
    }

    public void updateKuchen(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        //if fach an der stelle fachNummer leer ist, wird einfach der Kuchen hinzugef??gt.

        //throw exception if fachnummer nicht existiert
        if(fachNummer < 0 || fachNummer > faecherAnzahl-1){
            throw new Exception("es gibt kein Fach mit der Fachnummer "+ fachNummer);
        }
        Fach fach = new FachImpl(kuchen, name);
        faecher[fachNummer]= fach;

        Date date = new Date();
        kuchen.setInspektionsdatum(date);
    }

    public Verkaufskuchen eraseKuchen(int fachNummer) throws Exception {
        if(faecher[fachNummer] == null){
            throw new Exception("Fachnummer "+ fachNummer + " ist leer. es wird nichts geloescht");
        }
        Verkaufskuchen kuchen = faecher[fachNummer].getKuchen();
        faecher[fachNummer] = null;
        return kuchen;
    }

    public String showAllergene(){
        String erg = "";
        for(int i=0; i<faecherAnzahl; i++){
            if(faecher[i]!=null){
                if(faecher[i].getKuchen().getAllergene()!=null){
                    erg += "Fachnummer " + i+": " + faecher[i].getKuchen().getAllergene().toString() + System.lineSeparator();
                }
            }
        }
        return erg;
    }

    public String showNotIncludedAllergene(){
        String erg="Not Included Allergies: " + System.lineSeparator();
        Collection<Allergen> allergens = new LinkedList<>();
        for(int i=0; i<faecherAnzahl; i++){
            if(faecher[i]!=null){
                if(faecher[i].getKuchen().getAllergene()!=null){
                    allergens.addAll(faecher[i].getKuchen().getAllergene());
                }
            }
        }
        if(!allergens.contains(Allergen.Erdnuss)){ erg += "Erdnuss"+ System.lineSeparator(); }
        if(!allergens.contains(Allergen.Gluten)){ erg += "Gluten"+ System.lineSeparator(); }
        if(!allergens.contains(Allergen.Haselnuss)){ erg += "Haselnuss"+ System.lineSeparator();}
        if(!allergens.contains(Allergen.Sesamsamen)){ erg += "Sesamsamen"+ System.lineSeparator(); }
        return erg;
    }

    public int kuchenAnzahlInAutomat(){
        int count=0;
        for(int i=0; i<faecherAnzahl; i++){
            if(faecher[i] != null){ count++; }
        }
        return count;
    }

    public boolean isFull(){
        for(int i = 0; i<faecherAnzahl; i++) {
            if (faecher[i] == null){ return false; }
        }
        return true;
    }

    public boolean isEmpty(){
        for(int i = 0; i<faecherAnzahl; i++) {
            if (faecher[i] != null){ return false; }
        }
        return true;
    }

    public boolean fachBesetzt(int fachNummer){
        return getFaecher(fachNummer) != null;
    }

    //Getter
    public int getFaecherAnzahl() {
        return faecherAnzahl;
    }

    public Verkaufskuchen getKuchen(int fachNummer) {
        if(faecher[fachNummer] == null){
            return null;
        }else {
            return faecher[fachNummer].getKuchen();
        }
    }

    public Fach getFaecher(int fachNummer) {
        if(faecher[fachNummer]==null){
            return null;
        }else {
            return faecher[fachNummer];
        }
    }

    public List<Hersteller> getHerstellerList() {
        return herstellerList;
    }

    public Fach[] getFaecher() {
        return faecher;
    }

    //Setter
    public void setHerstellerList(List<Hersteller> herstellerList) {    //wird f??r jbp persistency ben??tigt
        this.herstellerList = herstellerList;
    }

    public void setFaecher(Fach[] faecher) {
        this.faecher = faecher;
    }

    //persistence
    public static void serialize(String filename, Collection<Automat> items){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
            serialize(oos,items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serialize(ObjectOutput objectOutput, Collection<Automat> items) throws IOException {
        objectOutput.writeObject(items);
    }

    public static Collection<Automat> deserialize(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return deserialize(ois);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Collection<Automat> deserialize(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        return (Collection<Automat>)objectInput.readObject();
    }

    //for testing purposes, fill the automat with 3 manufacturers and 6 cakes, 2 cakes for each type
    public void fillAutomat() throws Exception {
        Hersteller hersteller = new HerstellerImpl("hersteller");
        Hersteller hersteller2 = new HerstellerImpl("hersteller2");
        Hersteller hersteller3 = new HerstellerImpl("hersteller3");
        Obstkuchen obstkuchen = new ObstkuchenImpl("erdbeer", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Obstkuchen obstkuchen2 = new ObstkuchenImpl("banana", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Kremkuchen kremkuchen = new KremkuchenImpl("cream1", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Kremkuchen kremkuchen2 = new KremkuchenImpl("cream2", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        BigDecimal preis = BigDecimal.valueOf(5.5);
        Obsttorte obsttorte = new ObsttorteImpl("erdbeer", "cream1", hersteller2, 500, 50, preis);
        Obsttorte obsttorte2 = new ObsttorteImpl("banana", "cream2", hersteller2, 500, 50, preis);
        Collection<Allergen> Haselnuss = new ArrayList<>();
        Haselnuss.add(Allergen.Haselnuss);
        obsttorte.setAllergene(Haselnuss);
        Collection<Allergen> Gluten = new ArrayList<>();
        Haselnuss.add(Allergen.Gluten);
        kremkuchen.setAllergene(Gluten);
        Duration duration = Duration.ofDays(7);
        obstkuchen.setHaltbarkeit(duration);
        kremkuchen2.setPreis(BigDecimal.valueOf(5.5));

        this.addHersteller(hersteller);
        this.addHersteller(hersteller2);
        this.addHersteller(hersteller3);
        this.addKuchen(kremkuchen, "cream cake");
        this.addKuchen(kremkuchen2, "cream cake2");
        this.addKuchen(obstkuchen, "fruit cake");
        this.addKuchen(obstkuchen2, "fruit cake2");
        this.addKuchen(obsttorte, "fruit tart");
        this.addKuchen(obsttorte2, "fruit tart2");
    }


    //Beobachter
    private final List<Beobachter> beobachterList = new LinkedList<>();
    @Override public void meldeAn(Beobachter beobachter) {
        this.beobachterList.add(beobachter);
    }
    @Override public void meldeAb(Beobachter beobachter) {
        this.beobachterList.remove(beobachter);
    }
    @Override public void benachrichtige() {
        for (Beobachter beobachter : beobachterList) beobachter.aktualisiere();
    }

    //method used for loading machine persistency
    public void fillAutomat(Automat automat){
        this.faecherAnzahl = automat.getFaecherAnzahl();
        this.faecher = automat.getFaecher();
        this.herstellerList = automat.getHerstellerList();
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    //methods for sorting based on durability
    public Verkaufskuchen getFastestExpiryCake(List<Verkaufskuchen> list){
        if(list.size() == 0){
            return null;
        }else {
            Verkaufskuchen gesucht = this.getFirstCake();
            for (Verkaufskuchen verkaufskuchen : list) {
                if (verkaufskuchen != null) {
                    assert gesucht != null;
                    if (verkaufskuchen.getHaltbarkeit().compareTo(gesucht.getHaltbarkeit()) < 0)
                        gesucht = verkaufskuchen;
                }
            }
            return gesucht;
        }
    }

    private Verkaufskuchen getFirstCake() {
        if(kuchenAnzahlInAutomat()>0) {
            for (int i = 0; i<faecherAnzahl; i++){
                if(null != faecher[i]){
                    return this.faecher[i].getKuchen();
                }
            }
        }
        return null;
    }
}
