package simulationslogik.sim2;

import simulationslogik.lockedAutomat;
import simulationslogik.sim1.Consumer;

import java.util.Random;

public class Consumer2 extends Thread {
    private final lockedAutomat automat;
    public Consumer2(lockedAutomat automat){
        this.automat = automat;
    }

    public void run(){
        while (true) {
            try {
                int i = this.automat.locked_eraseKuchen2();
                if(i != -1) {
                    System.out.println("removed cake from locker " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
