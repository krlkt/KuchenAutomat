package model.geschaeftslogik.automat.belag;

import model.geschaeftslogik.automat.Allergen;
import model.geschaeftslogik.automat.HerstellerImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

class BelagImplTest {
    KuchenBodenImpl kuchenBoden = new KuchenBodenImpl("Obstkuchen", new HerstellerImpl("hersteller"),
            300, 30, BigDecimal.valueOf(3));
    Belag erdbeerKuchen = new BelagImpl(kuchenBoden, "erdbeer", 150, 20, BigDecimal.valueOf(1));

    Belag erdbeerBananaKuchen = new BelagImpl(erdbeerKuchen, "banana", 150, 25, BigDecimal.valueOf(2));

    @BeforeEach
    void setAllergene(){
        Collection<Allergen> kuchAllergen = new LinkedList<>();
        kuchAllergen.add(Allergen.Haselnuss);
        Collection<Allergen> erdbeerAllergen = new LinkedList<>();
        erdbeerAllergen.add(Allergen.Gluten);
        Collection<Allergen> bananaAllergen = new LinkedList<>();
        bananaAllergen.add(Allergen.Erdnuss);
        kuchenBoden.setAllergene(kuchAllergen);
        erdbeerKuchen.setAllergene(erdbeerAllergen);
        erdbeerBananaKuchen.setAllergene(bananaAllergen);
    }

    @Test
    void getName() {
        Assert.assertEquals(erdbeerBananaKuchen.getName(), "Obstkuchen, erdbeer, banana");
    }

    @Test
    void getPreis() {
        Assert.assertTrue(BigDecimal.valueOf(6).equals(erdbeerBananaKuchen.getPreis()));
    }

    @Test
    void getNaehrwert() {
        Assert.assertEquals(150+150+300, erdbeerBananaKuchen.getNaehrwert());
    }

    @Test
    void getHaltbarkeit() {
        Assert.assertTrue(Duration.ofHours(20).equals(erdbeerBananaKuchen.getHaltbarkeit()));
    }

    @Test
    void getAllergene() {
        Collection<Allergen> expectedAllergen = new LinkedList<>();
        expectedAllergen.add(Allergen.Erdnuss);
        expectedAllergen.add(Allergen.Gluten);
        expectedAllergen.add(Allergen.Haselnuss);
        Assert.assertEquals(expectedAllergen, erdbeerBananaKuchen.getAllergene());
    }
}