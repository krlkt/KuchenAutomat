import model.geschaeftslogik.automat.*;
import model.geschaeftslogik.persistence.JOS;
import java.util.Arrays;

public class JOS_Main {
    public static void main(String[] args) throws Exception {
        Automat automat = new Automat(10);
        automat.fillAutomat();  //fill automat with 3 manufacturer and 6 cakes
        JOS jos = new JOS();
        jos.save(automat);
        automat = jos.load();
        System.out.println(Arrays.toString(automat.showHerstellerList()));
        System.out.println(automat.showKuchenList());
    }
}
