import simulationslogik.sim2.Inspector;
import simulationslogik.lockedAutomat;
import simulationslogik.sim2.Consumer2;
import simulationslogik.sim2.Producer2;

public class simulation2 {
    public static void main(String[] args){
        lockedAutomat automat = new lockedAutomat(10);
        new Producer2(automat).start();
        new Inspector(automat).start();
        new Consumer2(automat).start();
    }
}
