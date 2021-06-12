package ui.controller.beobachter;

import geschaeftslogik.automat.Hersteller;
import geschaeftslogik.automat.HerstellerImpl;
import geschaeftslogik.automat.Obstkuchen;
import geschaeftslogik.automat.ObstkuchenImpl;
import org.junit.Assert;
import org.junit.Test;


public class BeobachterTest {
    ConsoleSubjekt s = new ConsoleSubjekt();
    Hersteller hersteller = new HerstellerImpl("hersteller");

    @Test
    public void ConsoleBeobachterAdd() throws Exception {
        ConsoleBeobachterAdd cb = new ConsoleBeobachterAdd(s);
        s.message="AdD";
        cb.aktualisiere();
        String is = "hersteller";
        Obstkuchen obstkuchen = new ObstkuchenImpl("erdbeer", hersteller);
        s.automat.addKuchen(obstkuchen, "erdbeer_kuchen", 1);
        Assert.assertEquals(s.automat.getKuchen(1),obstkuchen);
    }
}
