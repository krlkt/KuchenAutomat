package geschaeftslogik.automat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ObstkuchenImpl implements Obstkuchen, Serializable {
    String obstsorte;

    //Kuchen Attribute
    Hersteller hersteller;
    Collection<Allergen> allergene;
    int naehrwert;      //in kcal pro 100g
    Duration haltbarkeit;

    //Verkaufsobjekt Attribute
    BigDecimal preis;
    Date inspektionsDatum;
    int fachnummer;         //fängt von 1 - Anzahl von Faecher
    String name="obstkuchen";

    //Constructors
    public ObstkuchenImpl(String obstsorte, Hersteller hersteller){
        this.obstsorte = obstsorte;
        this.inspektionsDatum = new Date();
        this.hersteller = hersteller;
    }

    //getter
    @Override
    public String getObstsorte() {
        return obstsorte;
    }

    @Override
    public Hersteller getHersteller() {
        return hersteller;
    }

    @Override
    public String getName() {
        return name + ": " + obstsorte;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return allergene;
    }

    @Override
    public int getNaehrwert() {
        return naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }

    @Override
    public BigDecimal getPreis() {
        return preis;
    }

    @Override
    public Date getInspektionsdatum() {
        return inspektionsDatum;
    }



    @Override
    public int getFachnummer() {
        return fachnummer;
    }

    //setter
    public void setObstsorte(String obstsorte) {
        this.obstsorte = obstsorte;
    }

    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    public void setAllergene(Collection<Allergen> allergene) {
        this.allergene = allergene;
    }

    public void setNaehrwert(int naehrwert) {
        this.naehrwert = naehrwert;
    }

    public void setHaltbarkeit(Duration haltbarkeit) {
        this.haltbarkeit = haltbarkeit;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public void setInspektionsdatum(Date date) {
        this.inspektionsDatum = date;
    }

    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }
}
