package org.eriksommer.personregister;

import org.eriksommer.exceptions.*;
import org.eriksommer.personregister.Dato;

import java.time.LocalDate;

public class PersonValidator {

    public static String testValidNavn(String navn){
        //Støtter navn som består av én til fire ord.
        //De to første navnene kan ha bindestrek mellom navnene. eks: Markus-Johannes Pedersen
        String[] regex = {
                "[A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+[-][A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+[-][A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+"
        };

        for(String str : regex){
            if(navn.matches(str)){
                return navn;
            }
        }
        throw new InvalidNameException("Navnet du skrev inn er ikke gyldig");
    }

    public static Dato testValidDato(Dato dato) throws InvalidDatoException {
        if (dato.getManed() <= 0 || dato.getManed() > 12) {
            throw new InvalidDatoException("Måneden må være mellom 1 og 12");
        }
        int [] dager = {31,28,31,30,31,30,31,31,30,31,30,31};
        int dagerDenneManeden = dager[dato.getManed() - 1];

        if (dato.getAr() % 4 == 0 && dato.getManed() == 2){
            //Hvis det er skuddår har februar 29 dager. Må derfor plusse på 1 til antall dager i februar
            dagerDenneManeden += 1;
        }

        boolean[] testListe = {
                dato.getDag() > dagerDenneManeden,
                dato.getDag() <= 0,
                dato.getAr() > LocalDate.now().getYear(),
                dato.getAr() < (LocalDate.now().getYear() - 100),
                dato.getAr() == LocalDate.now().getYear() && dato.getManed() > LocalDate.now().getMonthValue(),
                dato.getAr() == LocalDate.now().getYear() && dato.getManed() == LocalDate.now().getMonthValue() &&
                dato.getDag() > LocalDate.now().getDayOfMonth()
        };

        for (boolean feil : testListe) {
            if (feil) {
                throw new InvalidDatoException("Denne datoen er ikke gyldig");
            }
        }
        return dato;
    }

    public static Dato testDatoInput(String dd, String mm, String yy) {
        int dag;
        int maned;
        int aar;

        try {
            dag = Integer.parseInt(dd);
            maned = Integer.parseInt(mm);
            aar = Integer.parseInt(yy);

            return new Dato(dag,maned,aar);
        }catch (Exception e){
            throw new InvalidInputException("Det er skrevet inn en ugyldig dato. Bruk tall");
        }
    }

    public static String testValidTelefon(String telefonnr) throws InvalidTelefonnrException {
        String[] regex = {
                "[0-9]{8}",
                "[+][4][7][0-9]{8}",
                "[0-9]{3}-[0-9]{4}",
                "[+][4][7] [0-9]{8}",
                "[0][0-9]{4} [0-9]{6}",
                "[0]{2} [4][7] [0-9]{8}",
                "[(][+][4][7][)][0-9]{8}",
                "[(][+][4][7][)] [0-9]{8}",
                "[+][4]{2} [0-9]{4} [0-9]{6}",
                "[(]541[)] [0-9]{3}-[0-9]{4}",
                "[1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[+][1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[0]{2}[1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[0]{2} [4][7] [0-9]{3} [0-9]{2} [0-9]{3}",
                "[(][+][4][7][)] [0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2}",
                //Amerikanske nummer
                "^[\\\\(]{0,1}([0-9]){3}[\\\\)]{0,1}[ ]?([^0-1]){1}([0-9]){2}[ ]?[-]?[ ]?([0-9]){4}[ ]*((x){0,1}([0-9]){1,5}){0,1}$"
        };

        for(String str : regex){
            if(telefonnr.matches(str)){
                return telefonnr;
            }
        }
        throw new InvalidTelefonnrException("Ugyldig telefonnummer");
    }

    public static String testValidEpost(String ePost) throws InvalidEpostException {
        String[] regex = {
                "[a-zæøåA-ZÆØÅ0-9.]+[@][a-z]+[.][a-z]+",
                "[a-zæøåA-ZÆØÅ0-9.]+[@][a-z]+[.][a-z]+[.][a-z]+"
        };

        for(String str : regex){
            if(ePost.matches(str)){
                return ePost;
            }
        }
        throw new InvalidEpostException("Skriv inn en gyldig epost");
    }
}