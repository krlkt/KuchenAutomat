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
                    int i = random.nextInt(automat.getFaecherAnzahl());
                    if(this.automat.locked_inspectKuchen(i)) {
                        System.out.println("inspected cake on locker number " + i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
