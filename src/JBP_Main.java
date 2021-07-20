import geschaeftslogik.automat.*;
import geschaeftslogik.persistence.JBP;

import java.util.Arrays;

public class JBP_Main {
    public static void main(String[] args) throws Exception {
        Automat automat = new Automat(10);
        automat.fillAutomat();
        JBP jbp = new JBP();
        jbp.save(automat);
        jbp.load();
        System.out.println(Arrays.toString(automat.showHerstellerList()));
        System.out.println(automat.showKuchenList());
    }
}
