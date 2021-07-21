import geschaeftslogik.automat.Hersteller;
import geschaeftslogik.automat.HerstellerImpl;
import geschaeftslogik.automat.Obstkuchen;
import geschaeftslogik.automat.ObstkuchenImpl;
import simulationslogik.sim2.Inspector;
import simulationslogik.lockedAutomat;
import simulationslogik.sim2.Consumer2;
import simulationslogik.sim2.Producer2;

import java.math.BigDecimal;

public class simulation2 {
    public static void main(String[] args) throws Exception {
        lockedAutomat automat = new lockedAutomat(100);
        automat.addHersteller("hersteller");

        for(int i=0;i<10;i++) new Producer2(automat).start();
        new Inspector(automat).start();
        for(int i=0;i<10;i++) new Consumer2(automat).start();
    }
}
