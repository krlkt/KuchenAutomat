import geschaeftslogik.automat.*;
import geschaeftslogik.persistence.JOS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class JOS_Main {
    public static void main(String[] args) throws Exception {
        Automat automat = new Automat(100);
        automat.fillAutomat();  //fill automat with 3 manufacturer and 6 cakes
        JOS jos = new JOS();
        jos.save(automat);
        jos.load();
    }
}
