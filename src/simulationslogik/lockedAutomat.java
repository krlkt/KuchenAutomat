package simulationslogik;

import geschaeftslogik.automat.Automat;
import geschaeftslogik.automat.Verkaufskuchen;
import geschaeftslogik.automat.Verkaufsobjekt;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lockedAutomat extends Automat {
    private final Lock lock=new ReentrantLock();
    private final Condition full=this.lock.newCondition();
    private final Condition empty=this.lock.newCondition();
    boolean adding = true;
    LinkedList<Integer> inspectedList = new LinkedList<>();

    public lockedAutomat(int faecherAnzahl) {
        super(faecherAnzahl);
    }

    //Methoden für Simulation 1
    public void locked_addKuchen(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        this.lock.lock();       //entering kritische Bereich
        try {
            while(this.fachBesetzt(fachNummer)) this.empty.await();
            if(null!=this.getFaecher(fachNummer))
                throw new IllegalStateException();
            this.addKuchen(kuchen, name, fachNummer);
            this.full.signal();
        }finally {
            this.lock.unlock();
        }
    }

    public Verkaufskuchen locked_eraseKuchen(int fachNummer) throws Exception {
        this.lock.lock();       //entering kritische Bereich
        try {
            while(!this.fachBesetzt(fachNummer)) this.full.await();
            if(null==this.getFaecher(fachNummer))
                throw new IllegalStateException();
            Verkaufskuchen kuchen = this.getKuchen(fachNummer);
            this.eraseKuchen(fachNummer);
            this.empty.signal();
            return kuchen;
        }finally {
            this.lock.unlock();
        }
    }

    //Methoden für Simulation 2
    public synchronized void locked_addKuchen2(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        while(!adding) wait();
        if(null!=this.getFaecher(fachNummer))
            throw new IllegalStateException();
        this.addKuchen(kuchen, name, fachNummer);
        if(this.isFull()) {
            adding = false;
        }
        notifyAll();
    }

    public synchronized void locked_eraseKuchen2() throws Exception {
        while(adding) wait();
        while(inspectedList.isEmpty()) wait();
        int toErase = inspectedList.getFirst();
        if(null != this.getKuchen(toErase)) this.eraseKuchen(toErase);
        if(this.isEmpty()) adding = true;
        notifyAll();
    }

    public boolean locked_inspectKuchen(int fachNummer) {
        if (null == this.getFaecher(fachNummer)) {
            return false;
        }
        Date date = new Date();
        this.getKuchen(fachNummer).setInspektionsdatum(date);
        return true;
    }
}
