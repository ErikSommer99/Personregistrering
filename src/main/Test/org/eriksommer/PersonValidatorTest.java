package org.eriksommer;

import org.eriksommer.exceptions.*;
import org.eriksommer.personregister.Dato;
import org.eriksommer.personregister.PersonValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonValidatorTest {

    @Test
    void testValidNavn() {
        PersonValidator.testValidNavn("Erik");
        PersonValidator.testValidNavn("Erik Storås");
        PersonValidator.testValidNavn("Erik Storås Sommer");
        PersonValidator.testValidNavn("Erik Storås Sommer Vinter");
        PersonValidator.testValidNavn("Erik-Johannes Sommer");
        PersonValidator.testValidNavn("Erik-Johannes Sommer Vinter");

    }

    @Test
    void testInvalidNavn(){
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("erik"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("Erik-Johannes"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("17"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("erik17"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("Erik17"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("Erik sommer"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("Erik storås Sommer"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("Erik StOrås Sommer"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("Erik St0rås Sommer"));
        assertThrows(InvalidNameException.class, () -> PersonValidator.testValidNavn("Dette er viktig!"));
    }

    @Test
    void testValidDato() {
        PersonValidator.testValidDato(new Dato(17,1,1999));
        PersonValidator.testValidDato(new Dato(1, 1, 1987));
        PersonValidator.testValidDato(new Dato(31, 7, 2000));
        PersonValidator.testValidDato(new Dato(12, 1, 2003));
        PersonValidator.testValidDato(new Dato(21, 11, 2019));
        PersonValidator.testValidDato(new Dato(12, 12, 2012));
        //Skuddår
        PersonValidator.testValidDato(new Dato(29, 2, 2016));
        PersonValidator.testValidDato(new Dato(29, 2, 2012));

    }

    @Test
    void testInvalidDato(){
        assertThrows(InvalidDatoException.class, () -> PersonValidator.testValidDato(new Dato(-1,3,2009)));
        assertThrows(InvalidDatoException.class, () -> PersonValidator.testValidDato(new Dato(4,13,2018)));
        assertThrows(InvalidDatoException.class, () -> PersonValidator.testValidDato(new Dato(21,10,20000)));
        assertThrows(InvalidDatoException.class, () -> PersonValidator.testValidDato(new Dato(29,2,2019)));
        assertThrows(InvalidDatoException.class, () -> PersonValidator.testValidDato(new Dato(29,13,2019)));

    }

    @Test
    void testDatoInput() {
        assertThrows(InvalidInputException.class, () -> PersonValidator.testDatoInput("jfn","3","2000"));
        assertThrows(InvalidInputException.class, () -> PersonValidator.testDatoInput("5","tger","2000"));
        assertThrows(InvalidInputException.class, () -> PersonValidator.testDatoInput("3","3","totusen"));
    }

    @Test
    void testValidTelefonnr() {
        PersonValidator.testValidTelefon("12233212");
        PersonValidator.testValidTelefon("00 47 12121212");
        PersonValidator.testValidTelefon("00 47 121 21 212");
        PersonValidator.testValidTelefon("00 47 121 21 212");
        PersonValidator.testValidTelefon("+4712233212");
        PersonValidator.testValidTelefon("+47 12233212");
        PersonValidator.testValidTelefon("(+47)12233212");
        PersonValidator.testValidTelefon("(+47) 12233212");
        PersonValidator.testValidTelefon("(+47) 12 23 32 12");
        PersonValidator.testValidTelefon("07911 123456");
        PersonValidator.testValidTelefon("+44 7911 123456");
        PersonValidator.testValidTelefon("754-3010");
        PersonValidator.testValidTelefon("(541) 754-3010");
        PersonValidator.testValidTelefon("+1-541-754-3010");
        PersonValidator.testValidTelefon("1-541-754-3010");
        PersonValidator.testValidTelefon("001-541-754-3010");
    }

    @Test
    void testInvalidTelefonnr(){
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon(""));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("7654321"));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("NaN"));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("-231"));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("123-norway"));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("1-541-æøå-3010"));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("1-541-abc-3010"));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("!%&/"));
        assertThrows(InvalidTelefonnrException.class, () -> PersonValidator.testValidTelefon("123 123     123 12"));
    }

    @Test
    void testInvalidEpost() {
        assertThrows(InvalidEpostException.class, () -> PersonValidator.testValidEpost(""));
        assertThrows(InvalidEpostException.class, () -> PersonValidator.testValidEpost("henrik.lieng"));
        assertThrows(InvalidEpostException.class, () -> PersonValidator.testValidEpost("@oslomet.no"));
        assertThrows(InvalidEpostException.class, () -> PersonValidator.testValidEpost("henrik.lieng@invalidDomain"));
        assertThrows(InvalidEpostException.class, () -> PersonValidator.testValidEpost("test@"));
        assertThrows(InvalidEpostException.class, () -> PersonValidator.testValidEpost(";bot@evil.com"));

    }

    @Test
    void testValidEpost(){
        PersonValidator.testValidEpost("henrik.lieng@oslomet.no");
        PersonValidator.testValidEpost("example@example.com");
        PersonValidator.testValidEpost("uk@domain.co.uk");
    }
}