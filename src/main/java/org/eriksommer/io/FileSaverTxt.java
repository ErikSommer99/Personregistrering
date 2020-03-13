package org.eriksommer.io;

import org.eriksommer.personregister.DataCollection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSaverTxt implements FileSaver {
    @Override
    public void save(DataCollection collection, String lagretFilvei) throws IOException {
        var utFil = collection.utTxtFil();
        try {
            Files.write(Paths.get(lagretFilvei), utFil.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
