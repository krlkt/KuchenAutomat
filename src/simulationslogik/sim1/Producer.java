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
                        this.automat.locked_addKuchen(Util.list.get(random.nextInt(4)), "egal", randomFachnummer.nextInt(automat.getFaecherAnzahl()));
                        System.out.println("added a cake");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
