package mockito;

import geschaeftslogik.automat.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {
    @Test
    void addKuchenToAutomat() throws Exception {
        //mock Verkaufskuchen und Fach
        Verkaufskuchen mockV = mock(Verkaufskuchen.class);
        Fach mockF = mock(Fach.class);

        //lege Verkaufskuchen in den Fach
        when(mockF.getKuchen()).thenReturn(mockV);

        //test addKuchen
        Automat automat = new Automat(100);
        automat.addKuchen(mockF.getKuchen(), "kuchenA",1);

        assertEquals(mockV, automat.getKuchen(1));
    }

    @Test
    void fachIstBesetzt(){
        //mock objekt, der in den Fach gelegt wird
        Kremkuchen mockK = mock(Kremkuchen.class);

        //Kuchen in den Fach anlegen
        Fach fach = new FachImpl(mockK, "krem kuchen");

        assertEquals(true, fach.istBesetzt());
    }


}
