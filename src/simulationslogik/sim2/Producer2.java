package simulationslogik.sim2;

import simulationslogik.Util;
import simulationslogik.lockedAutomat;

import java.util.Random;

public class Producer2 extends Thread{
    private final lockedAutomat automat;
    private Random random = new Random();
    private Random randomFachnummer = new Random(77);
    int counter=0;

    public Producer2(lockedAutomat automat) {
        this.automat = automat;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.automat) {
                if (!this.automat.isFull()) {
                    try {
                        this.automat.locked_addKuchen2(Util.list.get(random.nextInt(4)), "egal", counter++%automat.getFaecherAnzahl());
                        System.out.println("added a cake");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
