package model.geschaeftslogik.automat.belag;

import model.geschaeftslogik.automat.Verkaufskuchen;

public abstract class Belag implements Verkaufskuchen {
    protected Verkaufskuchen kuchenBoden;

    public Belag(Verkaufskuchen kuchenBoden){
        this.kuchenBoden = kuchenBoden;
    }
}
