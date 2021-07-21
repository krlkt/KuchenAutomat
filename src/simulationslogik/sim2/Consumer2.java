package simulationslogik.sim2;

import simulationslogik.lockedAutomat;
import simulationslogik.sim1.Consumer;

import java.util.Random;

public class Consumer2 extends Thread {
    private final lockedAutomat automat;
    private Random random = new Random(77);
    public Consumer2(lockedAutomat automat){
        this.automat = automat;
    }

    public void run(){
        while (true) {
            try {
                if(this.automat.locked_eraseKuchen2()) {
                    System.out.println("removed cake");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
