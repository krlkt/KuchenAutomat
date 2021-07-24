import model.simulationslogik.sim2.Inspector;
import model.simulationslogik.lockedAutomat;
import model.simulationslogik.sim2.Consumer2;
import model.simulationslogik.sim2.Producer2;

public class simulation2 {
    public static void main(String[] args) throws Exception {
        lockedAutomat automat = new lockedAutomat(100);
        automat.addHersteller("hersteller");

        for(int i=0;i<10;i++) new Producer2(automat).start();
        new Inspector(automat).start();
        for(int i=0;i<10;i++) new Consumer2(automat).start();
    }
}
