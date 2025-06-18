package xyz.dedsecm.icar.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatutCovoiturageTest {
    @Test
    void testValues() {
        StatutCovoiturage[] values = StatutCovoiturage.values();
        assertEquals(3, values.length);
        assertArrayEquals(new StatutCovoiturage[]{
            StatutCovoiturage.RESERVABLE,
            StatutCovoiturage.EN_COURS,
            StatutCovoiturage.ANNULE
        }, values);
    }

    @Test
    void testValueOf() {
        assertEquals(StatutCovoiturage.RESERVABLE, StatutCovoiturage.valueOf("RESERVABLE"));
        assertEquals(StatutCovoiturage.EN_COURS, StatutCovoiturage.valueOf("EN_COURS"));
        assertEquals(StatutCovoiturage.ANNULE, StatutCovoiturage.valueOf("ANNULE"));
    }
}

