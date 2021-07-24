package model.simulationslogik;

import model.geschaeftslogik.automat.*;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

public class lockedAutomatTest {
    lockedAutomat automat = new lockedAutomat(4);
    Hersteller hersteller = new HerstellerImpl("hersteller");
    Obstkuchen obstkuchenA = new ObstkuchenImpl("Erdbeeren, Kiwis", hersteller, 500, 50, BigDecimal.valueOf(5.5));
    Obstkuchen obstkuchenB = new ObstkuchenImpl("Bananen, Mango", hersteller, 500, 50, BigDecimal.valueOf(5.5));

    @Test
    public void locked_addKuchen() throws Exception {
        automat.addHersteller(hersteller);
        automat.locked_addKuchen(obstkuchenA, "obsA", 0);
        automat.locked_addKuchen(obstkuchenB, "obsB", 1);

        Assert.assertEquals(automat.getKuchen(0), obstkuchenA);
        Assert.assertEquals(automat.getKuchen(1), obstkuchenB);
    }

    @Test
    public void locked_eraseKuchen() throws Exception {
        automat.addHersteller(hersteller);
        automat.locked_addKuchen(obstkuchenA, "obsA", 0);
        Assert.assertEquals(automat.getKuchen(0), obstkuchenA);
        automat.locked_eraseKuchen(0);
        automat.locked_addKuchen(obstkuchenB, "obsB", 0);
        Assert.assertEquals(obstkuchenB, automat.getKuchen(0));
    }

    @Test
    public void getOldestDate() throws Exception {
        automat.addHersteller(hersteller);
        automat.locked_addKuchen(obstkuchenB, "obsB", 3);
        Thread.sleep(1);
        automat.locked_addKuchen(obstkuchenA, "obsA", 2);
        automat.locked_addKuchen(obstkuchenA, "obsA", 1);
        Assert.assertEquals(automat.getOldestDate(), 3);
    }

    @Test
    public void locked_eraseKuchen2() throws Exception {
        automat.addHersteller(hersteller);
        automat.locked_addKuchen2(obstkuchenA, "obsA", 0);
        automat.locked_addKuchen2(obstkuchenB, "obsB", 1);
        automat.locked_addKuchen2(obstkuchenB, "obsB", 2);
        automat.locked_addKuchen2(obstkuchenA, "obsA", 3);
        automat.locked_eraseKuchen2();
        Thread.sleep(10);
        automat.locked_eraseKuchen2();

        Assert.assertEquals(null, this.automat.getFaecher(0));
        Assert.assertEquals(null, this.automat.getFaecher(1));
    }

    @Test
    public void locked_addKuchen2() throws Exception {
        automat.addHersteller(hersteller);
        automat.locked_addKuchen2(obstkuchenA, "obsA", 0);
        automat.locked_addKuchen2(obstkuchenB, "obsB", 1);

        Assert.assertEquals(automat.getKuchen(0), obstkuchenA);
        Assert.assertEquals(automat.getKuchen(1), obstkuchenB);
    }

    @Test
    public void locked_eraseKuchen3() throws Exception {
        automat.addHersteller(hersteller);
        automat.locked_addKuchen2(obstkuchenA, "obsA", 0);
        Thread.sleep(1);
        automat.locked_addKuchen2(obstkuchenB, "obsB", 1);
        Thread.sleep(1);
        automat.locked_addKuchen2(obstkuchenB, "obsB", 2);
        Thread.sleep(1);
        automat.locked_addKuchen2(obstkuchenA, "obsA", 3);
        Thread.sleep(1);
        automat.locked_eraseKuchen3(4); //erase 4 Kuchen vom Automat

        Assert.assertEquals(automat.kuchenAnzahlInAutomat(), 0);    //sollte 0 übrig sein
    }
}