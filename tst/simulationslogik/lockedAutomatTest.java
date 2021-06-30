package simulationslogik;

import geschaeftslogik.automat.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class lockedAutomatTest {
    lockedAutomat automat = new lockedAutomat(1000);
    Hersteller hersteller = new HerstellerImpl("hersteller");
    Obstkuchen obstkuchenA = new ObstkuchenImpl("Erdbeeren, Kiwis", hersteller, 500, 50, BigDecimal.valueOf(5.5));
    Obstkuchen obstkuchenB = new ObstkuchenImpl("Bananen, Mango", hersteller, 500, 50, BigDecimal.valueOf(5.5));

    @Test
    public void locked_addKuchen() throws Exception {
        automat.locked_addKuchen(obstkuchenA, "obsA", 0);
        automat.locked_addKuchen(obstkuchenB, "obsB", 1);

        Assert.assertEquals(automat.getKuchen(0), obstkuchenA);
        Assert.assertEquals(automat.getKuchen(1), obstkuchenB);
    }

    @Test
    public void locked_eraseKuchen() throws Exception {
        automat.locked_addKuchen(obstkuchenA, "obsA", 0);
        Assert.assertEquals(automat.getKuchen(0), obstkuchenA);
        automat.locked_eraseKuchen(0);
        automat.locked_addKuchen(obstkuchenB, "obsB", 0);
        Assert.assertEquals(obstkuchenB, automat.getKuchen(0));
    }
}