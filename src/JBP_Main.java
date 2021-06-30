import geschaeftslogik.automat.*;
import geschaeftslogik.persistence.JBP;

public class JBP_Main {
    public static void main(String[] args) throws Exception {
        Automat automat = new Automat(100);
        automat.fillAutomat();
        JBP jbp = new JBP();
        jbp.save(automat);
        jbp.load();
    }
}
