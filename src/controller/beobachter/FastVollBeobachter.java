package controller.beobachter;
import controller.events.Console;
import geschaeftslogik.automat.Automat;

public class FastVollBeobachter implements Beobachter{
    private Automat automat;
    public FastVollBeobachter(Automat automat){
        this.automat = automat;
        this.automat.meldeAn(this);
    }

    @Override
    public void aktualisiere() {
        double d = (double) automat.getCakeInAutomat() / automat.getFaecherAnzahl();
        double percent = d*100;
        if( d > 0.9){
            System.out.println("Machine is (almost) full. Current percentage: " + percent + "%");
        }
    }
}
