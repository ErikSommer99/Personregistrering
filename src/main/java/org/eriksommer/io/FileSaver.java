package org.eriksommer.io;

import org.eriksommer.personregister.DataCollection;

import java.io.IOException;

public interface FileSaver {

    void save(DataCollection collection, String filvei) throws IOException;

}
