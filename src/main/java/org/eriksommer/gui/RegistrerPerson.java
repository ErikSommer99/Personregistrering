package org.eriksommer.gui;

import org.eriksommer.personregister.Dato;
import org.eriksommer.personregister.Person;
import org.eriksommer.personregister.PersonValidator;
import org.eriksommer.exceptions.*;

public class RegistrerPerson {

    public Person opprettObjekt(String navnet, String dag, String maned, String ar, String telnr, String ePost){
        try {
            String navn = PersonValidator.testValidNavn(navnet);
            Dato dato = PersonValidator.testValidDato(PersonValidator.testDatoInput(dag, maned, ar));
            String telefonnr = PersonValidator.testValidTelefon(telnr);
            String epost = PersonValidator.testValidEpost(ePost);
            return new Person(navn, dato, telefonnr, epost);

        }catch (InvalidNameException | InvalidEpostException | InvalidInputException | InvalidDatoException | InvalidTelefonnrException e){
            if (navnet.isBlank() && dag.isBlank() && maned.isBlank() &&
                    ar.isBlank() && telnr.isBlank() && ePost.isBlank()){
                Dialogs.showErrorDialog("Datafeltene er tomme", "Skriv inn i boksene");
            }else {
                Dialogs.showErrorDialog("Feil input", e.getMessage());
            }
            return null;
        }
    }
}
