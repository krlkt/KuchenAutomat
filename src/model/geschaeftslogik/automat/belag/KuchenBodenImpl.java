package model.geschaeftslogik.automat.belag;

import model.geschaeftslogik.automat.Allergen;
import model.geschaeftslogik.automat.Hersteller;
import model.geschaeftslogik.automat.Verkaufskuchen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class KuchenBodenImpl implements Verkaufskuchen {
    String kuchenTyp;

    //Verkaufskuchen Attribute
    Hersteller hersteller;
    int naehrwert;
    Duration haltbarkeit;
    BigDecimal preis;
    Collection<Allergen> allergene = new LinkedList<>();
    Date inspektionsDatum;
    int fachNummer;

    public KuchenBodenImpl(String kuchenTyp, Hersteller hersteller, int naehrwert, long haltbarkeitAsHours, BigDecimal preis){
        this.kuchenTyp = kuchenTyp;
        this.hersteller = hersteller;
        this.naehrwert = naehrwert;
        this.haltbarkeit = Duration.ofHours(haltbarkeitAsHours);
        this.preis = preis;
    }


    public String getKuchenTyp() {
        return kuchenTyp;
    }

    public void setKuchenTyp(String kuchenTyp) {
        this.kuchenTyp = kuchenTyp;
    }

    @Override
    public BigDecimal getPreis() {
        return this.preis;
    }

    @Override
    public Hersteller getHersteller() {
        return this.hersteller;
    }

    @Override
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    @Override
    public void setNaehrwert(int naehrwert) {
        this.naehrwert = naehrwert;
    }

    @Override
    public void setHaltbarkeit(Duration duration) {
        this.haltbarkeit = duration;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return this.allergene;
    }

    @Override
    public int getNaehrwert() {
        return this.naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return this.haltbarkeit;
    }

    @Override
    public void setAllergene(Collection<Allergen> allergene) {
        this.allergene = allergene;
    }

    @Override
    public int getFachnummer() {
        return this.fachNummer;
    }

    @Override
    public void setFachnummer(int fachNummer) {
        this.fachNummer = fachNummer;
    }

    @Override
    public Date getInspektionsdatum() {
        return inspektionsDatum;
    }

    @Override
    public void setInspektionsdatum(Date date) {
        this.inspektionsDatum = date;
    }

    @Override
    public String getName() {
        return kuchenTyp;
    }

    @Override
    public void setName(String name) {
        this.kuchenTyp = name;
    }
}
