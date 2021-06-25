import geschaeftslogik.automat.*;
import geschaeftslogik.persistence.JBP;

import java.beans.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JBP_Main {
    public static void main(String[] args) throws Exception {
        Automat automat = new Automat(100);
        automat.fillAutomat();
        JBP jbp = new JBP();
        jbp.save(automat);
        jbp.load();
    }
}
