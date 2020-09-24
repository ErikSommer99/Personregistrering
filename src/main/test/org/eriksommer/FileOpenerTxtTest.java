package org.eriksommer;

import org.eriksommer.exceptions.InvalidPersonFormatException;
import org.eriksommer.io.FileOpenerTxt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenerTxtTest {

    @Test
    void parseNumber() throws InvalidPersonFormatException{
        assertEquals(FileOpenerTxt.parseNumber("92203303", null),92203303);
        assertNotEquals(FileOpenerTxt.parseNumber("92203304", null), 92203303);
    }
}