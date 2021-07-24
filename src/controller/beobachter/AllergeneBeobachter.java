package controller.beobachter;

import model.geschaeftslogik.automat.Automat;

public class AllergeneBeobachter implements Beobachter{
    String alteAllergen;
    private Automat automat;
    public AllergeneBeobachter(Automat automat){
        this.automat = automat;
        this.automat.meldeAn(this);
        alteAllergen = this.automat.showAllergene();
    }

    @Override
    public void aktualisiere() {
        String neuAllergen = this.automat.showAllergene();
        if(!neuAllergen.equalsIgnoreCase(alteAllergen)){
            System.out.println("Allergies on machine has been changed");
        }
        this.alteAllergen = neuAllergen;
    }
}
