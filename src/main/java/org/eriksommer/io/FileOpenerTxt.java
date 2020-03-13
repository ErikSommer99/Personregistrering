package org.eriksommer.io;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.eriksommer.personregister.Dato;
import org.eriksommer.personregister.Person;
import org.eriksommer.personregister.PersonValidator;
import org.eriksommer.exceptions.InvalidDatoFormatException;
import org.eriksommer.exceptions.InvalidPersonFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOpenerTxt implements FileOpener {

    public ObservableList<Person> read(String path) throws IOException{
        ObservableList<Person> listeFil = FXCollections.observableArrayList();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            String linje;

            while ((linje = reader.readLine()) != null){
                listeFil.add(parsePerson(linje));
            }
        }
        return listeFil;
    }

    public static Person parsePerson(String linje) throws InvalidPersonFormatException {
        String [] split = linje.split(";");
        if (split.length != 6){
            throw new InvalidPersonFormatException("Må bruke ; for å separere de 6 ulike verdiene");
        }

        String navn = split[0];
        Dato dato = new Dato(parseNumber(split[1], "Dagen er feil"), parseNumber(split[2], "Måneden er feil"),
                parseNumber(split[3], "Året er feil"));
        String telefonnr = split[4];
        String epost = split[5];

        return new Person(navn, dato, telefonnr, epost);
    }

    public static int parseNumber(String telefonnr, String errorMessage) throws InvalidPersonFormatException{
        int nummer;
        try {
            nummer = Integer.parseInt(telefonnr);
        }catch (NumberFormatException e){
            throw new InvalidPersonFormatException(errorMessage);
        }
        return nummer;
    }

    public static Dato parseDato(String dato){
        String[] nummer = dato.split("[.]");

        if(nummer.length != 3){
            throw new InvalidDatoFormatException("Feil lengde på datoen");
        }

        if (!(nummer[0].matches("[0-9]+") && nummer[1].matches("[0-9]+") && nummer[2].matches("[0-9]{4}"))){
            throw new InvalidDatoFormatException("Gale tegn for tall");
        }

        int dag;
        int maned;
        int ar;
        try{
            dag = Integer.parseInt(nummer[0]);
            maned = Integer.parseInt(nummer[1]);
            ar = Integer.parseInt(nummer[2]);
        }catch (Exception e){
            throw new InvalidDatoFormatException("Klarte ikke å parse til heltall");
        }

        return PersonValidator.testValidDato(new Dato(dag, maned, ar));
    }
}
