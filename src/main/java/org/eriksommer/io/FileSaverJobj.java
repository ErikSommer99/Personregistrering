package org.eriksommer.io;

import org.eriksommer.personregister.DataCollection;
import org.eriksommer.personregister.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileSaverJobj implements FileSaver {

    public void save(DataCollection collection, String lagretFilvei) throws IOException {
        var liste = collection.getListe();
        try {
            FileOutputStream filUt = new FileOutputStream(lagretFilvei);
            ObjectOutputStream objektUt = new ObjectOutputStream(filUt);
            objektUt.writeObject(new ArrayList<Person>(liste));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
