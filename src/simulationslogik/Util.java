package simulationslogik;

import geschaeftslogik.automat.*;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Verkaufskuchen> list = createCakeList();

    private static List<Verkaufskuchen> createCakeList() {
        List<Verkaufskuchen> list = new ArrayList<>();
        Hersteller hersteller = new HerstellerImpl("hersteller");
        Kremkuchen kremkuchen1 = new KremkuchenImpl("kremsort1", hersteller);
        Kremkuchen kremkuchen2 = new KremkuchenImpl("kremsort2", hersteller);
        Obstkuchen obstkuchen1 = new ObstkuchenImpl("obstsort1", hersteller);
        Obstkuchen obstkuchen2 = new ObstkuchenImpl("obstsort2", hersteller);
        list.add(kremkuchen1);
        list.add(kremkuchen2);
        list.add(obstkuchen1);
        list.add(obstkuchen2);
        return list;
    }
}
