package simulationslogik.sim3;

import simulationslogik.lockedAutomat;

import java.util.Random;

public class Consumer3 extends Thread{
    private final lockedAutomat automat;
    public Consumer3(lockedAutomat automat){
        this.automat = automat;
    }
    Random randomAnzahl = new Random();

    public void run(){
        while (true) {
            try {
                int i = this.automat.locked_eraseKuchen3(randomAnzahl.nextInt(automat.getFaecherAnzahl()));
                if(i != 0) {
                    System.out.println("removed " + i + " cake(s) from locker");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
