package model.geschaeftslogik.automat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class AutomatTest {
    Automat automat = null;
    Hersteller hersteller = null;

    Obstkuchen obstkuchenA = null;
    Obstkuchen obstkuchenB = null;

    @Before
    public void addHerstellerToAutomat(){
        automat = new Automat(100);
        hersteller = new HerstellerImpl("hersteller");
        obstkuchenA  = new ObstkuchenImpl("Erdbeeren, Kiwis", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        obstkuchenB = new ObstkuchenImpl("Bananen, Mango", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        automat.addHersteller(hersteller);
    }

    @Test
    public void createHersteller(){
        Hersteller oatker = new HerstellerImpl("Dr. Oatker");
        Assert.assertEquals(oatker.getName(),"Dr. Oatker");
    }

    @Test
    public void addHersteller(){
        automat.addHersteller("andy");
        Assert.assertTrue(automat.addHersteller("bob"));
    }

    @Test
    public void addHersteller_ButNameAlreadyExistOnList(){
        automat.addHersteller("Andy");
        Assert.assertFalse(automat.addHersteller("aNdy"));
    }

    @Test
    public void showHerstellerList() throws Exception {
        automat.addHersteller("Andy");
        automat.addHersteller("bob");
        automat.addHersteller("phillip");
        System.out.println(Arrays.toString(automat.showHerstellerList()));
    }

    @Test
    public void createKuchenOnAutomat() throws Exception {
        automat.addKuchen(obstkuchenA, "obstkuchenA",60);
        Assert.assertEquals(automat.getKuchen(60), obstkuchenA);
    }

    @Test
    public void addKuchen() throws Exception {
        automat.addKuchen(obstkuchenA,"obstA");
        Assert.assertEquals(automat.getKuchen(0),obstkuchenA);
        automat.addKuchen(obstkuchenB,"obstB");
        Assert.assertEquals(automat.getKuchen(1),obstkuchenB);
    }

    @Test(expected = Exception.class)
    public void vollAutomat() throws Exception {
        for(int i=0; i< automat.getFaecherAnzahl();i++) {
            automat.addKuchen(obstkuchenA, "obstkuchen");
        }
        automat.addKuchen(obstkuchenA,"obsA");
    }

    @Test(expected = Exception.class)
    public void zweiKuchenAufEinemFach() throws Exception {
        automat.addKuchen(obstkuchenA, "obstkuchenA", 0);
        automat.addKuchen(obstkuchenB, "obstkuchenB",0);
    }

    @Test(expected = Exception.class)
    public void kuchenAufUng??ltigenFachNummerGelegt() throws Exception {
        automat.addKuchen(obstkuchenA, "obstkuchenA",100);    //es existiert Faecher nur vom nummer 0 bis 99
    }

    @Test
    public void showKuchenOnAutomat() throws Exception {
        automat.addKuchen(obstkuchenA, "obstkuchenA", 5);
        automat.addKuchen(obstkuchenB, "obstkuchenB");
        Duration duration = Duration.ofDays(10);
        obstkuchenA.setHaltbarkeit(duration);
        System.out.println(automat.showKuchenList());

        Assert.assertEquals(automat.getKuchen(5),obstkuchenA);
        Assert.assertEquals(automat.getKuchen(5).getFachnummer(),5);
    }

    @Test
    public void showKuchenOnAutomatWithType() throws Exception {
        automat.addKuchen(obstkuchenA, "obstkuchenA",5);
        automat.addKuchen(obstkuchenB, "obstkuchenB",99);
        Kremkuchen kremB = new KremkuchenImpl("cream", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        automat.addKuchen(kremB, "kremkuchenB");
        BigDecimal preis = BigDecimal.valueOf(5.5);
        Obsttorte obsttorteA = new ObsttorteImpl("eredbeer", "cream", hersteller, 500, 50, preis);
        automat.addKuchen(obsttorteA, "obstorteA");
        Obsttorte obsttorteB = new ObsttorteImpl("eredbeer", "cream", hersteller, 500, 50, preis);
        automat.addKuchen(obsttorteB, "obstorteB");
        Obsttorte obsttorteC = new ObsttorteImpl("eredbeer", "cream", hersteller, 500, 50, preis);
        automat.addKuchen(obsttorteC, "obstorteC");
        Kremkuchen kremA = new KremkuchenImpl("cream", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        automat.addKuchen(kremA, "kremkuchenA");

        System.out.println(automat.showKuchenList("obstkuchen"));
    }

    @Test
    public void updateKuchenOnAutomat() throws Exception {
        automat.addKuchen(obstkuchenA, "obstkuchenA",0);
        automat.updateKuchen(obstkuchenB, "obstkuchenB", 0);

        Assert.assertEquals(automat.getKuchen(0),obstkuchenB);
        System.out.println(obstkuchenB.getInspektionsdatum());
    }

    @Test
    public void eraseKuchenOnAutomat() throws Exception {
        automat.addKuchen(obstkuchenA, "obstkuchenA",0);
        automat.eraseKuchen(0);

        Assert.assertNull(automat.getFaecher(0));
    }

    @Test(expected = Exception.class)
    public void eraseEmptyKuchenOnAutomat() throws Exception {
        automat.eraseKuchen(0);
    }

    @Test
    public void isNotFull() throws Exception {
        for(int i=0; i< automat.getFaecherAnzahl() - 1; i++){
            automat.addKuchen(obstkuchenA, "obsA", i);
        }
        boolean shouldBeFalse = automat.isFull();
        Assert.assertFalse(shouldBeFalse);
    }

    @Test
    public void isFull() throws Exception {
        for(int i=0; i< automat.getFaecherAnzahl(); i++){
            automat.addKuchen(obstkuchenA, "obsA", i);
        }
        boolean shouldBeTrue = automat.isFull();
        Assert.assertTrue(shouldBeTrue);
    }

    @Test
    public void fachBesetzt() throws Exception {
        Assert.assertFalse(automat.fachBesetzt(0));
        automat.addKuchen(obstkuchenA, "obsA", 0);
        Assert.assertTrue(automat.fachBesetzt(0));
    }


    @Test
    public void testAddHersteller() {
        Hersteller hersteller1 = new HerstellerImpl("hersteller1");
        Assert.assertTrue(automat.addHersteller(hersteller1));
        Assert.assertEquals(automat.getHerstellerList().get(1), hersteller1);
    }

    @Test
    public void testRemoveHersteller() {
        Hersteller hersteller1 = new HerstellerImpl("hersteller1");
        Hersteller hersteller2 = new HerstellerImpl("hersteller2");

        Assert.assertTrue(automat.addHersteller(hersteller1));
        Assert.assertTrue(automat.removeHersteller("hersteller1"));
        automat.addHersteller(hersteller2);
        Assert.assertEquals(automat.getHerstellerList().get(1), hersteller2);
    }

    @Test
    public void showAllergene() throws Exception {
        Collection<Allergen> allergenErdGlut = new LinkedList<>();
        allergenErdGlut.add(Allergen.Erdnuss);
        allergenErdGlut.add(Allergen.Gluten);
        Collection<Allergen> allergenHas = new LinkedList<>();
        allergenHas.add(Allergen.Haselnuss);

        obstkuchenA.setAllergene(allergenErdGlut);
        obstkuchenB.setAllergene(allergenHas);

        automat.addKuchen(obstkuchenA, "obstkuchenA");
        automat.addKuchen(obstkuchenB, "obstkuchenB");
        System.out.println(automat.showAllergene());
    }

    @Test
    public void showNotIncludedAllergene() throws Exception {
        Collection<Allergen> allergenErdGlut = new LinkedList<>();
        allergenErdGlut.add(Allergen.Erdnuss);
        allergenErdGlut.add(Allergen.Gluten);
        Collection<Allergen> allergenHas = new LinkedList<>();
        allergenHas.add(Allergen.Haselnuss);

        obstkuchenA.setAllergene(allergenErdGlut);
        obstkuchenB.setAllergene(allergenHas);

        automat.addKuchen(obstkuchenA, "obstkuchenA");
        automat.addKuchen(obstkuchenB, "obstkuchenB");
        System.out.println(automat.showNotIncludedAllergene());
    }

    @Test
    public void getCakeInAutomat() throws Exception {
        automat.fillAutomat();
        automat.addKuchen(obstkuchenA, "obstkuchenA");
        automat.addKuchen(obstkuchenB, "obstkuchenB");
        Assert.assertEquals(automat.kuchenAnzahlInAutomat(), 8);
    }

    @Test
    public void getFastestExpiryCake() throws Exception {
        List<Verkaufskuchen> cakes = new LinkedList<>();
        Obstkuchen obstkuchenC = new ObstkuchenImpl("Bananen, Mango", hersteller, 500, 40, BigDecimal.valueOf(5.5));
        automat.addKuchen(obstkuchenA, "obsA", 5);
        automat.addKuchen(obstkuchenB, "obsB");
        automat.addKuchen(obstkuchenC, "obsC", 7);

        cakes.add(obstkuchenA);
        cakes.add(obstkuchenB);
        cakes.add(obstkuchenC);
        Assert.assertEquals(automat.getFastestExpiryCake(cakes), obstkuchenC);
    }

}
