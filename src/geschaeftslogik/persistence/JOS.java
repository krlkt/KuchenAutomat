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

    public void load(){
        Collection<Automat> loadedItems= Automat.deserialize("items.ser");
        for (Automat i : loadedItems) {
            System.out.println(Arrays.toString(i.showHerstellerList()));
            System.out.println(i.showKuchenList());
        }
    }
}
