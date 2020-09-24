package org.eriksommer;

import org.eriksommer.exceptions.InvalidAgeException;
import org.eriksommer.personregister.Alder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlderTest {

    @Test
    void finnAlder() {
        assertEquals(21, Alder.finnAlder(17,1,1999), 0);
        assertNotEquals(21,Alder.finnAlder(12,8,1999),0);

        assertThrows(InvalidAgeException.class, () -> Alder.finnAlder(-1,0,1899));
        assertThrows(InvalidAgeException.class, () -> Alder.finnAlder(15,4,1800));
    }
}