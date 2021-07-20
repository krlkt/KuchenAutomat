package controller.beobachter;

import geschaeftslogik.automat.Automat;

public class AllergeneBeobachter implements Beobachter{
    private Automat automat;
    public AllergeneBeobachter(Automat automat){
        this.automat = automat;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {

    }
}
