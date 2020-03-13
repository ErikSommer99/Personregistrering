package org.eriksommer.io;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.eriksommer.personregister.Person;

import java.io.*;
import java.util.ArrayList;

public class FileOpenerJobj implements FileOpener {

    @SuppressWarnings("unchecked")
    public ObservableList<Person> read(String path) throws IOException, ClassNotFoundException {

        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream(path))) {

            ArrayList<Person> listeFil = (ArrayList<Person>) innfil.readObject();

            return FXCollections.observableArrayList(listeFil);
        }
    }
}
