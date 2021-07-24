package model.geschaeftslogik.automat;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class FachImplTest {

    @Test
    void istBesetzt() {
        //mock objekt, der in den Fach gelegt wird
        Kremkuchen mockK = mock(Kremkuchen.class);

        //Kuchen in den Fach anlegen
        Fach fach = new FachImpl(mockK, "krem kuchen");

        assertTrue(fach.istBesetzt());
    }
}