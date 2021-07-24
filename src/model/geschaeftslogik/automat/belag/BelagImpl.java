package model.geschaeftslogik.automat.belag;

import model.geschaeftslogik.automat.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class BelagImpl extends Belag{
    String name;
    Duration haltbarkeitAsHours;
    Collection<Allergen> allergene = new LinkedList<>();
    int naehrwert;
    BigDecimal preis;

    public BelagImpl(Verkaufskuchen kuchenBoden, String name, int naehrwert, long haltbarkeitAsHours, BigDecimal preis) {
        super(kuchenBoden);
        this.name = name;
        this.naehrwert = naehrwert;
        this.haltbarkeitAsHours = Duration.ofHours(haltbarkeitAsHours);
        this.preis = preis;
    }

    @Override
    public String getName() {
        String erg = kuchenBoden.getName() + ", " + this.name;
        return erg;
    }

    @Override
    public BigDecimal getPreis() {
        return kuchenBoden.getPreis().add(this.preis);
    }

    @Override
    public int getNaehrwert() {
        return kuchenBoden.getNaehrwert() + this.naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        if(kuchenBoden.getHaltbarkeit().compareTo(haltbarkeitAsHours) >= 0) {
            return haltbarkeitAsHours;
        }else{
            return kuchenBoden.getHaltbarkeit();
        }
    }

    @Override
    public Date getInspektionsdatum() {
        return kuchenBoden.getInspektionsdatum();
    }

    @Override
    public int getFachnummer() {
        return kuchenBoden.getFachnummer();
    }

    @Override
    public Hersteller getHersteller() {
        return kuchenBoden.getHersteller();
    }

    @Override
    public Collection<Allergen> getAllergene() {
        Collection<Allergen> allergens = allergene;
        allergens.addAll(kuchenBoden.getAllergene());
        return allergens;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        this.haltbarkeitAsHours = duration;
    }

    @Override
    public void setAllergene(Collection<Allergen> allergene) {
        this.allergene = allergene;
    }

    @Override
    public void setInspektionsdatum(Date date) {
        kuchenBoden.setInspektionsdatum(date);
    }

    @Override
    public void setFachnummer(int fachNummer) {
        kuchenBoden.setFachnummer(fachNummer);
    }
}
