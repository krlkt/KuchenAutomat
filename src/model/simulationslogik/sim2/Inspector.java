package simulationslogik.sim2;

import simulationslogik.lockedAutomat;

import java.util.Random;

public class Inspector extends Thread{
    private final lockedAutomat automat;
    private Random random = new Random(77);
    public Inspector(lockedAutomat automat){
        this.automat = automat;
    }

    public void run(){
        while (true) {
                try {
                    int i = random.nextInt(automat.getFaecherAnzahl());
                    if(this.automat.locked_inspectKuchen(i)) {
                        //System.out.println("inspected cake on locker number " + i);
                        //wird kommentiert, da sonst wird auf der Console fast nur Zeilen von Inspector geprintet.
                        //weil die Laufzeit von Inspector viel schneller ist als Producer2 und Consumer2
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
