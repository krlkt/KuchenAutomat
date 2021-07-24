package model.geschaeftslogik.automat.belag;

import model.geschaeftslogik.automat.Allergen;
import model.geschaeftslogik.automat.Hersteller;
import model.geschaeftslogik.automat.Verkaufskuchen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class KuchenBoden implements Verkaufskuchen {
    

    @Override
    public BigDecimal getPreis() {
        return null;
    }

    @Override
    public Date getInspektionsdatum() {
        return null;
    }

    @Override
    public void setInspektionsdatum(Date date) {

    }

    @Override
    public int getFachnummer() {
        return 0;
    }

    @Override
    public void setFachnummer(int fachNummer) {

    }

    @Override
    public Hersteller getHersteller() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setPreis(BigDecimal preis) {

    }

    @Override
    public void setNaehrwert(int naehrwert) {

    }

    @Override
    public void setHaltbarkeit(Duration duration) {

    }

    @Override
    public Collection<Allergen> getAllergene() {
        return null;
    }

    @Override
    public int getNaehrwert() {
        return 0;
    }

    @Override
    public Duration getHaltbarkeit() {
        return null;
    }

    @Override
    public void setAllergene(Collection<Allergen> allergene) {

    }
}
