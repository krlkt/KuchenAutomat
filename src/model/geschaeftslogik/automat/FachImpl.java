package model.geschaeftslogik.automat;

import java.io.Serializable;

public class FachImpl implements Fach, Serializable {
    Verkaufskuchen kuchen;

    //Constructors
    public FachImpl(Verkaufskuchen kuchen, String name){
        this.kuchen = kuchen;
        this.kuchen.setName(name);
    }

    //getter
    @Override
    public int getFachnummer() {
        return kuchen.getFachnummer();
    }

    @Override
    public Verkaufskuchen getKuchen() {
        return kuchen;
    }

    @Override
    public String getName() {
        return kuchen.getName();
    }

    //hilfsmethoden
    @Override
    public boolean istBesetzt() {
        if(kuchen != null){
            return true;
        }
        return false;
    }
}
