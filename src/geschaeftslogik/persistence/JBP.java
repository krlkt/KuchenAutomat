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
            encoder.setPersistenceDelegate(KremkuchenImpl.class,new DefaultPersistenceDelegate(new String[]{ "kremsorte", "hersteller" }) );
            encoder.setPersistenceDelegate(ObstkuchenImpl.class,new DefaultPersistenceDelegate(new String[]{ "obstsorte", "hersteller" }) );
            encoder.setPersistenceDelegate(ObsttorteImpl.class,new DefaultPersistenceDelegate(new String[]{ "obstsorte","kremsorte","hersteller" }) );
            encoder.setPersistenceDelegate(HerstellerImpl.class,new DefaultPersistenceDelegate(new String[]{ "name" }) );
            //encoder.setPersistenceDelegate(BigDecimal.class, new DefaultPersistenceDelegate(new String[]{"intVal"}));
            //encoder.setPersistenceDelegate(Duration.class, new DefaultPersistenceDelegate(new String[]{"seconds"}));

            //TODO BIGDECIMAL UND DURATION
            
            BeanInfo info = Introspector.getBeanInfo(Automat.class);
            for (PropertyDescriptor pd : info.getPropertyDescriptors()){
                if(pd.getName().equals("herstellerList")){
                    pd.setReadMethod(Automat.class.getMethod("getHerstellerList"));
                    pd.setWriteMethod(Automat.class.getMethod("setHerstellerList", List.class));
                }
                if(pd.getName().equals("faecher")){
                    pd.setReadMethod(Automat.class.getMethod("getFaecher"));
                    pd.setWriteMethod(Automat.class.getMethod("setFaecher", Fach[].class));
                }
            }
            encoder.writeObject(items);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        try (XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("JBP.xml")));){
            List<Automat> loadedList=(List<Automat>)decoder.readObject();
            for (Automat i:loadedList) {
                System.out.println(Arrays.toString(i.showHerstellerList()));
                System.out.println(i.showKuchenList());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}