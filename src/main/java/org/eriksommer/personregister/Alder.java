package org.eriksommer.personregister;

import org.eriksommer.exceptions.InvalidAgeException;

import java.time.LocalDate;

public class Alder {
    public static int finnAlder(int dag, int maned, int aar) throws InvalidAgeException {
        int alder;

        if(LocalDate.now().getMonthValue() > maned || (LocalDate.now().getMonthValue() == maned
                && LocalDate.now().getDayOfMonth() >= dag)) {

            alder = LocalDate.now().getYear() - aar;
        } else{
            alder = LocalDate.now().getYear() - aar - 1;
        }

        if(alder < 0 || alder >= 120){
            throw new InvalidAgeException("Alderen er ugyldig");
        }

        return alder;
    }
}
