package geschaeftslogik.persistence;

import geschaeftslogik.automat.*;

import java.beans.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JBP {
    public JBP(){
    }

    public void save(Automat automat) throws Exception {
        ArrayList<Automat> items=new ArrayList<>();
        items.add(automat);
        try(XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("JBP.xml")));){
            encoder.setPersistenceDelegate(Automat.class,new DefaultPersistenceDelegate(new String[]{ "faecherAnzahl" }) );
            encoder.setPersistenceDelegate(FachImpl.class,new DefaultPersistenceDelegate(new String[]{ "kuchen", "name" }) );
            encoder.setPersistenceDelegate(KremkuchenImpl.class,new DefaultPersistenceDelegate(new String[]{ "kremsorte", "hersteller", "naehrwert", "haltbarkeitToHours", "preis" }) );
            encoder.setPersistenceDelegate(ObstkuchenImpl.class,new DefaultPersistenceDelegate(new String[]{ "obstsorte", "hersteller", "naehrwert", "haltbarkeitToHours", "preis" }) );
            encoder.setPersistenceDelegate(ObsttorteImpl.class,new DefaultPersistenceDelegate(new String[]{ "obstsorte","kremsorte","hersteller", "naehrwert","haltbarkeitToHours","preis" }) );
            encoder.setPersistenceDelegate(HerstellerImpl.class,new DefaultPersistenceDelegate(new String[]{ "name" }) );
            // Handling BigDecimal
            //Quelle: https://stackoverflow.com/questions/4602210/xmlencoder-in-java-for-serialization
            encoder.setPersistenceDelegate(BigDecimal.class, new DefaultPersistenceDelegate() {
                protected Expression instantiate(Object oldInstance, Encoder out) {
                    BigDecimal bd = (BigDecimal) oldInstance;
                    return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[] {
                            bd.toString()
                    });
                }protected boolean mutatesTo(Object oldInstance, Object newInstance) { return oldInstance.equals(newInstance); }
            });
//
//            BeanInfo info = Introspector.getBeanInfo(Verkaufskuchen.class);
//            for (PropertyDescriptor pd : info.getPropertyDescriptors()){
//                if(pd.getName().equals("haltbarkeit")){
//                    pd.setReadMethod(Verkaufskuchen.class.getMethod("getHaltbarkeit"));
//                    pd.setWriteMethod(Verkaufskuchen.class.getMethod("setHaltbarkeit", Duration.class));
//                }
//                if(pd.getName().equals("preis")){
//                    pd.setReadMethod(Verkaufskuchen.class.getMethod("getPreis"));
//                    pd.setWriteMethod(Verkaufskuchen.class.getMethod("setPreis", BigDecimal.class));
//                }
//            }
            encoder.writeObject(items);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Automat load() {
        Automat automat = null;
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("JBP.xml")));) {
            List<Automat> loadedList = (List<Automat>) decoder.readObject();
            for (Automat i : loadedList) {
                System.out.println(Arrays.toString(i.showHerstellerList()));
                System.out.println(i.showKuchenList());
                automat = i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return automat;
    }
}
