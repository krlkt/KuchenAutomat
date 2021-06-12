package geschaeftslogik.automat;

import java.math.BigDecimal;
import java.util.Date;

public interface Verkaufsobjekt {
    BigDecimal getPreis();
    Date getInspektionsdatum();
    void setInspektionsdatum(Date date);
    int getFachnummer();
    void setFachnummer(int fachNummer);
    Hersteller getHersteller();
    String getName();
    void setName(String name);
}
