package simulationslogik.sim1;

import simulationslogik.lockedAutomat;

import java.util.Random;

public class Consumer extends Thread{
    private final lockedAutomat automat;
    private Random random = new Random(77);
    public Consumer(lockedAutomat automat){
        this.automat = automat;
    }

    public void run(){
            while (true) {
                try {
                    int i = random.nextInt(automat.getFaecherAnzahl());
                    this.automat.locked_eraseKuchen(i);
                    System.out.println(Thread.currentThread() + ": removed cake on locker " + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}
