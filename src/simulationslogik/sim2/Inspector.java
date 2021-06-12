package simulationslogik.sim2;

import simulationslogik.lockedAutomat;

import java.util.Random;

public class Inspector extends Thread{
    private final lockedAutomat automat;
    private Random random = new Random();
    public Inspector(lockedAutomat automat){
        this.automat = automat;
    }

    public void run(){
        while (true) {
            synchronized (this.automat) {

                try {
                    System.out.println("inspected cake: " + this.automat.locked_inspectKuchen(random.nextInt(automat.getFaecherAnzahl())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
