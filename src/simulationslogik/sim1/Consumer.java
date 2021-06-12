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
                    System.out.println("removed cake: " + this.automat.locked_eraseKuchen(random.nextInt(automat.getFaecherAnzahl())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}
