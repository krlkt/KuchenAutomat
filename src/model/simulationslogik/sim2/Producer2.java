package simulationslogik.sim2;

import simulationslogik.Util;
import simulationslogik.lockedAutomat;

import java.util.Random;

public class Producer2 extends Thread{
    private final lockedAutomat automat;
    private Random random = new Random();
    private Random randomFachnummer = new Random(77);

    public Producer2(lockedAutomat automat) {
        this.automat = automat;
    }

    @Override
    public void run() {
        while (true) {
            if (!this.automat.isFull()) {
                try {
                    int j = randomFachnummer.nextInt(automat.getFaecherAnzahl());
                    if(this.automat.locked_addKuchen2(Util.list.get(random.nextInt(4)), "kuchen", j)) {
                        System.out.println("added cake to locker number " + j);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
