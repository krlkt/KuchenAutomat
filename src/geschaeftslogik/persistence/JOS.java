package geschaeftslogik.persistence;

import geschaeftslogik.automat.Automat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class JOS {

    public JOS(){}

    public void save(Automat automat){
        ArrayList<Automat> items=new ArrayList<>();
        items.add(automat);
        Automat.serialize("items.ser",items);
    }

    public Automat load(){
        Automat automat = new Automat(100);
        Collection<Automat> loadedItems= Automat.deserialize("items.ser");
        for (Automat i : loadedItems) {
            automat = i;
        }
        return automat;
    }
}
