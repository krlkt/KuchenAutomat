package model.simulationslogik;

import model.geschaeftslogik.automat.Automat;
import model.geschaeftslogik.automat.Verkaufskuchen;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lockedAutomat extends Automat {
    private final Lock lock=new ReentrantLock();
    private final Condition automatFull=this.lock.newCondition();
    private final Condition automatEmpty=this.lock.newCondition();
    private boolean adding = true;

    public lockedAutomat(int faecherAnzahl) {
        super(faecherAnzahl);
    }

    //Methoden für Simulation 1
    public synchronized void locked_addKuchen(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        if(null==this.getFaecher(fachNummer)) {
            this.addKuchen(kuchen, name, fachNummer);
        }
        //else do nothing
    }

    public synchronized void locked_eraseKuchen(int fachNummer) throws Exception {
        if(null!=this.getFaecher(fachNummer)) {
            this.eraseKuchen(fachNummer);
        }
        //else do nothing
    }

    //Methoden für Simulation 2
    public boolean locked_addKuchen2(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        this.lock.lock();       //entering kritische Bereich
        try {
            while(!adding) this.automatEmpty.await();
            if(null!=this.getFaecher(fachNummer)) return false;
            locked_addKuchen(kuchen, name, fachNummer);
            if(this.isFull()){
                adding = false;
                this.automatFull.signal();
            }
            return true;
        }finally {
            this.lock.unlock();
        }
    }

    public int locked_eraseKuchen2() throws Exception {
        this.lock.lock();       //entering kritische Bereich
        try {
            while(adding) this.automatFull.await();
            int oldestDate = this.getOldestDate();
            if(null==this.getFaecher(oldestDate)){
                return -1;
            }
            locked_eraseKuchen(oldestDate);
            if(this.isEmpty()){
                adding = true;
                this.automatEmpty.signal();
            }
            return oldestDate;
        }finally {
            this.lock.unlock();
        }
    }

    public int getOldestDate() {
            Date oldestDate = new Date();
            int erg = 0;
            for (int i = 0; i < getFaecherAnzahl(); i++) {
                if (null != getFaecher(i)) {
                    if (null != this.getKuchen(i)) {
                        if (this.getKuchen(i).getInspektionsdatum().before(oldestDate)) {
                            oldestDate = this.getKuchen(i).getInspektionsdatum();
                            erg = i;
                        }
                    }
                }
            }
            return erg;
    }

    public boolean locked_inspectKuchen(int fachNummer) {
        if (null == this.getFaecher(fachNummer)) {
            return false;
        }
        Date date = new Date();
        this.getKuchen(fachNummer).setInspektionsdatum(date);
        return true;
    }

    //Methoden für Simulation 3
    public boolean locked_addKuchen3(Verkaufskuchen kuchen, String name, int fachNummer) throws Exception {
        this.lock.lock();       //entering kritische Bereich
        try {
            if(null!=this.getFaecher(fachNummer)) return false;
            locked_addKuchen(kuchen, name, fachNummer);
            return true;
        }finally {
            this.lock.unlock();
        }
    }

    public int locked_eraseKuchen3(int Anzahl) throws Exception {
        this.lock.lock();       //entering kritische Bereich
        int counter=0;
        try {
            while(adding) this.automatFull.await();
            for(int i=0;i<Anzahl;i++) {
                int oldestDate = this.getOldestDate();
                if (null == this.getFaecher(oldestDate)) {
                    //counter stays
                }else {
                    locked_eraseKuchen(oldestDate);
                    counter++;
                    if(this.isEmpty()){
                        adding = true;
                        this.automatEmpty.signal();
                    }
                }
            }
        }finally {
            this.lock.unlock();
        }
        return counter;
    }
}
