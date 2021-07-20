package simulationslogik.sim1;

import simulationslogik.Util;
import simulationslogik.lockedAutomat;

import java.util.Random;

public class Producer extends Thread{
    private final lockedAutomat automat;
    private Random random = new Random();
    private Random randomFachnummer = new Random(77);

    public Producer(lockedAutomat automat) {
        this.automat = automat;
    }

    @Override
    public void run() {
        while (true) {
                if (!this.automat.isFull()) {
                    try {
                        int i = random.nextInt(6);
                        int j = randomFachnummer.nextInt(automat.getFaecherAnzahl());
                        this.automat.locked_addKuchen(Util.list.get(i), "kuchen", j);
                        System.out.println(Thread.currentThread() + ": added cake " + Util.list.get(i).getName() + " to locker number " + j);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
