package org.eriksommer.io;

import javafx.collections.ObservableList;
import org.eriksommer.personregister.Person;

import java.io.IOException;

public interface FileOpener {

    public ObservableList<Person> read(String path) throws IOException, ClassNotFoundException;

}
