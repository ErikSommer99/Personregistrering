package org.eriksommer.gui;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import org.eriksommer.personregister.DataCollection;
import org.eriksommer.io.*;

import java.io.File;
import java.io.IOException;

public class Filbehandler {

    @SuppressWarnings("unchecked")
    static void lastFil(DataCollection collection, TableView tableView, Label lblMelding){
        String filVei;
        FileOpener opener = null;
        FileChooser filVelger = new FileChooser();
        File filValgt = filVelger.showOpenDialog(null);

        String filType = "";
        if (filValgt != null) {
            int i = filValgt.getName().lastIndexOf('.');
            if (i > 0) {
                filType = filValgt.getName().substring(i + 1);
            }

            filVei = filValgt.getAbsolutePath();

            switch (filType){
                case "txt" : opener = new FileOpenerTxt();
                    break;
                case "jobj" : opener = new FileOpenerJobj();
                    break;
                default: Dialogs.showErrorDialog("Feil i filåpner","Du kan bare åpne .txt eller .jobj filer");
            }

            if (opener != null){
                try {
                    collection.leggTil(opener.read(filVei));
                    tableView.setItems(collection.getListe());
                    lblMelding.setText(filType + "-filen ble korrekt lastet");
                }catch (IOException | ClassNotFoundException e){
                    Dialogs.showErrorDialog("Klarte ikke å åpne filen", "Grunn: "+e.getMessage());
                }
            }
        }else {
            filVei = null;
            lblMelding.setText("Filvalget ble kansellert");
        }
    }

    static void lagreFil(DataCollection collection, Search newSearch, TableView tableView, Label lblMelding){
        String lagretFilvei;
        FileSaver saver = null;
        FileChooser filvelger = new FileChooser();
        File filvalgt = filvelger.showSaveDialog(null);

        DataCollection collectionToSave = collection;

        if(!newSearch.getListe().isEmpty()) {
            collectionToSave = new DataCollection(newSearch.getListe());
        }

        String filType = "";
        if (filvalgt != null){
            if (!tableView.getItems().isEmpty()){
                int i = filvalgt.getName().lastIndexOf('.');
                if (i > 0) {
                    filType = filvalgt.getName().substring(i+1);
                }
                lagretFilvei = filvalgt.getAbsolutePath();

                switch (filType){
                    case "txt" : saver = new FileSaverTxt();
                        break;
                    case "jobj" : saver = new FileSaverJobj();
                        break;
                    default: Dialogs.showErrorDialog("Feil fil", "Du kan bare lagre til enten .txt eller .jobj filer");
                }

                if (saver != null){
                    try {
                        saver.save(collectionToSave, lagretFilvei);
                        lblMelding.setText("Listen ble korrekt lastet til ny ." + filType +"-fil");
                    }catch (IOException e){
                        Dialogs.showErrorDialog("", e.getMessage());
                    }
                }
            }else {
                Dialogs.showErrorDialog("Feil i lagring","Du kan ikke lagre en tom liste");
            }
        }else {
            lblMelding.setText("Lagringen ble kansellert");
        }
    }
}
