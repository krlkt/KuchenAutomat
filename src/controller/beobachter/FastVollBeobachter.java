package controller.beobachter;
import model.geschaeftslogik.automat.Automat;

public class FastVollBeobachter implements Beobachter{
    private Automat automat;
    public FastVollBeobachter(Automat automat){
        this.automat = automat;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        double d = (double) automat.kuchenAnzahlInAutomat() / automat.getFaecherAnzahl();
        double percent = d*100;
        if( d > 0.9){
            System.out.println("Machine is (almost) full. Current percentage: " + percent + "%");
        }
    }
}
