package model.geschaeftslogik.persistence;

import model.geschaeftslogik.automat.Automat;
import model.geschaeftslogik.automat.HerstellerImpl;
import model.geschaeftslogik.automat.ObstkuchenImpl;
import model.geschaeftslogik.automat.Verkaufskuchen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class JOSTest {
    JOS jos = new JOS();
    Automat automat = new Automat(100);
    Verkaufskuchen obstkuchen = new ObstkuchenImpl("obs", new HerstellerImpl("hersteller"), 10, 100, BigDecimal.valueOf(3));

    @Test
    void saveAndLoad() {
        automat.addHersteller("hersteller");
        try {
            automat.addKuchen(obstkuchen, "obs", 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        jos.save(automat);
        Automat automat2 = jos.load();
        Assert.assertEquals(automat2.getKuchen(0).getName(), obstkuchen.getName());
        Assert.assertEquals(automat2.getKuchen(0).getFachnummer(), obstkuchen.getFachnummer());
    }
}