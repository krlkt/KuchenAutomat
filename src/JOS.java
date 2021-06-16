import geschaeftslogik.automat.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class JOS {
    public static void main(String[] args) throws Exception {
        ArrayList<AutomatSerializable> items=new ArrayList<>();
        AutomatSerializable automat = new AutomatSerializable(100);
        automat.fillAutomat();
        items.add(automat);
        for (AutomatSerializable i : items) {System.out.println(Arrays.toString(i.showHerstellerList()));
            System.out.println(i.showKuchenList());}
        AutomatSerializable.serialize("items.ser",items);
        System.out.println("----------------------");
        Collection<AutomatSerializable> loadedItems= AutomatSerializable.deserialize("items.ser");
        for (AutomatSerializable i : loadedItems) {System.out.println(Arrays.toString(i.showHerstellerList()));
            System.out.println(i.showKuchenList());}
    }
}
