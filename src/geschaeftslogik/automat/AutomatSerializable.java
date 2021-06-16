package geschaeftslogik.automat;

import java.io.*;
import java.util.Collection;

public class AutomatSerializable extends Automat implements Serializable {
    static final long serialVersionUID=1L;

    public AutomatSerializable reference;

    public AutomatSerializable(int faecherAnzahl) {
        super(faecherAnzahl);
    }

    public static void serialize(String filename, Collection<AutomatSerializable> items){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
            serialize(oos,items);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void serialize(ObjectOutput objectOutput, Collection<AutomatSerializable> items) throws IOException {
        objectOutput.writeObject(items);
    }

    public static Collection<AutomatSerializable> deserialize(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return deserialize(ois);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Collection<AutomatSerializable> deserialize(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        return (Collection<AutomatSerializable>)objectInput.readObject();
    }

}
