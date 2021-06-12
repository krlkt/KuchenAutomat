package geschaeftslogik.automat;

import java.util.*;

public class Automat {
    /**
     * Anzahl von faecher musst bei der Erstellung von Automat eingegeben werden
     */
    private final int faecherAnzahl;
    private Fach[] faecher;
    private List<Hersteller> herstellerList = new ArrayList<>();

    //Constructors
    public Automat(int faecherAnzahl){
        this.faecherAnzahl = faecherAnzahl;
        this.faecher = new Fach[faecherAnzahl];
    }

    //getter
    public int getFaecherAnzahl() {
        return faecherAnzahl;
    }

    public Verkaufskuchen getKuchen(int fachNummer) {
        return faecher[fachNummer].getKuchen();
    }

    public Fach getFaecher(int fachNummer) {
        return faecher[fachNummer];
    }

    //Hauptmethoden
    public boolean addHersteller(String name){
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

    int kuchenAnzahl(Hersteller hersteller){ //TODO: schoener?
        int count=0;
        for(int i=0; i<faecherAnzahl; i++){
            if(faecher[i]!=null){
                if(faecher[i].getKuchen().getHersteller().getName().equalsIgnoreCase(hersteller.getName())){count++;}
            }
        }
        return count;
    }

    public boolean removeHersteller(String name){
        //check ob bei Kuchen gelöschende name gibt
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

    //kuchen automatisch ins freie Fach hinzufügen
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
                String fn = Integer.toString(faecher[i].getFachnummer());
                ergebnis += fn +": " + faecher[i].getName() + System.lineSeparator();
            }
        }
        return ergebnis;
    }

    public String showKuchenList(String type) { //TODO schoener?
        String ergebnis="";
        switch(type){
            case "obstkuchen":
                for(int i=0; i<faecherAnzahl;i++){
                    if(faecher[i] != null && faecher[i].getKuchen() instanceof ObstkuchenImpl){
                        String fn = Integer.toString(faecher[i].getFachnummer());
                        ergebnis += fn +": " + faecher[i].getName() + System.lineSeparator();
                    }
                }  break;
            case "kremkuchen":
                for(int i=0; i<faecherAnzahl;i++){
                    if(faecher[i] != null && faecher[i].getKuchen() instanceof KremkuchenImpl){
                        String fn = Integer.toString(faecher[i].getFachnummer());
                        ergebnis += fn +": " + faecher[i].getName() + System.lineSeparator();
                    }
                } break;
            case "obsttorte":
                for(int i=0; i<faecherAnzahl;i++){
                    if(faecher[i] != null && faecher[i].getKuchen() instanceof ObsttorteImpl){
                        String fn = Integer.toString(faecher[i].getFachnummer());
                        ergebnis += fn +": " + faecher[i].getName() + System.lineSeparator();
                    }
                } break;
            default:
                return showKuchenList();
        }
        return ergebnis;
    }

    public void updateKuchen(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        //if fach an der stelle fachNummer leer ist, wird einfach der Kuchen hinzugefügt.

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
        String erg = new String();
        for(int i=0; i<faecherAnzahl; i++){
            if(faecher[i]!=null){
                if(faecher[i].getKuchen().getAllergene()!=null){
                    erg += i+": " + faecher[i].getKuchen().getAllergene().toString() + System.lineSeparator();;
                }
            }
        }
        return erg;
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

    public List<Hersteller> getHerstellerList() {
        return herstellerList;
    }

    public void setFaecher(Fach[] faecher) {
        this.faecher = faecher;
    }
}
