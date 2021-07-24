import model.simulationslogik.sim1.Consumer;
import model.simulationslogik.sim1.Producer;
import model.simulationslogik.lockedAutomat;

public class simulation1 {

    public static void main(String[] args){
        lockedAutomat automat = new lockedAutomat(100);
        automat.addHersteller("hersteller");
        new Producer(automat).start();
        new Consumer(automat).start();
    }
}
