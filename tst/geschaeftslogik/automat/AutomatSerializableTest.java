package geschaeftslogik.automat;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AutomatSerializableTest {

    @org.junit.jupiter.api.Test
    void serialize() {
        ObjectOutput oos=mock(ObjectOutput.class);
        Collection<Automat> items=new ArrayList<>();
        items.add(mock(Automat.class));
        items.add(mock(Automat.class));
        try {
            Automat.serialize(oos,items);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        try {
            verify(oos).writeObject(any());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void deserialize() {
        ObjectInput objectInput=mock(ObjectInput.class);
        try {
            Collection<Automat> items=Automat.deserialize(objectInput);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        try {
            verify(objectInput).readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}