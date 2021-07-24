import model.simulationslogik.lockedAutomat;
import model.simulationslogik.sim2.Inspector;
import model.simulationslogik.sim3.Consumer3;
import model.simulationslogik.sim3.Producer3;

public class simulation3 {
    public static void main(String[] args) {
        lockedAutomat automat = new lockedAutomat(50);
        automat.addHersteller("hersteller");

        for(int i=0; i<5; i++) new Producer3(automat).start();
        new Inspector(automat).start();
        for(int i=0; i<5; i++) new Consumer3(automat).start();
    }
}
