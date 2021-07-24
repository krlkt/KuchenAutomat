package simulationslogik;

import model.geschaeftslogik.automat.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Verkaufskuchen> list = createCakeList();

    private static List<Verkaufskuchen> createCakeList() {
        List<Verkaufskuchen> list = new ArrayList<>();
        Hersteller hersteller = new HerstellerImpl("hersteller");
        Kremkuchen kremkuchen1 = new KremkuchenImpl("kremsort1", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Kremkuchen kremkuchen2 = new KremkuchenImpl("kremsort2", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Obstkuchen obstkuchen1 = new ObstkuchenImpl("obstsort1", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Obstkuchen obstkuchen2 = new ObstkuchenImpl("obstsort2", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Obstkuchen obsttorte = new ObsttorteImpl("obstsorte1", "kremsort1", hersteller, 500, 50, BigDecimal.valueOf(5.5));
        Obstkuchen obsttorte2 = new ObsttorteImpl("obsttorte2", "kremsort2", hersteller, 500, 50, BigDecimal.valueOf(5.5));

        list.add(kremkuchen1);
        list.add(kremkuchen2);
        list.add(obstkuchen1);
        list.add(obstkuchen2);
        list.add(obsttorte);
        list.add(obsttorte2);

        return list;
    }
}
