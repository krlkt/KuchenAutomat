package geschaeftslogik.automat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class KremkuchenImpl implements Kremkuchen, Serializable {
    String kremsorte;

    //Kuchen Attribute
    Hersteller hersteller;

    Collection<Allergen> allergene;
    int naehrwert;      //in kcal pro 100g
    Duration haltbarkeit;

    //Verkaufsobjekt Attribute
    BigDecimal preis;
    Date inspektionsDatum;
    int fachnummer;         //fängt von 1 - Anzahl von Faecher
    String name="kremkuchen";

    //Constructors
    public KremkuchenImpl(String kremsorte, Hersteller hersteller){
        this.kremsorte = kremsorte;
        this.inspektionsDatum = new Date();
        this.hersteller = hersteller;
    }

    //setter und getter
    @Override
    public String getKremsorte() {
        return this.kremsorte;
    }

    @Override
    public Hersteller getHersteller() {
        return this.hersteller;
    }

    @Override
    public String getName() {
        return name + ": " + kremsorte;
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
        this.haltbarkeit = haltbarkeit;
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
    public BigDecimal getPreis() {
        return this.preis;
    }

    @Override
    public Date getInspektionsdatum() {
        return this.inspektionsDatum;
    }

    @Override
    public void setInspektionsdatum(Date date) {
        this.inspektionsDatum = date;
    }

    @Override
    public int getFachnummer() {
        return this.fachnummer;
    }

    @Override
    public void setFachnummer(int fachNummer) {
        this.fachnummer = fachNummer;
    }
}
