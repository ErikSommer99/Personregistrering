package org.eriksommer.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import org.eriksommer.personregister.DataCollection;
import org.eriksommer.personregister.Person;
import org.eriksommer.exceptions.InvalidInputException;

public class Search {
    ObservableList<String> sokeListe = FXCollections.observableArrayList();
    ObservableList<Person> sokeResultatListe = FXCollections.observableArrayList();

    public void search(DataCollection collection, String sokeVerdi, String sokeOrd, TableView<Person> tableView){
        sokeResultatListe.clear();

        if (sokeOrd.isBlank()){
            throw new InvalidInputException("Søkefeltet er tomt");
        }

        switch (sokeVerdi){
            case "Navn" : sokeResultatListe.addAll(collection.sokNavn(sokeOrd));
            break;
            case "Alder" : try {
                                sokeResultatListe.addAll(collection.sokAlder(Integer.parseInt(sokeOrd)));
                           }catch (Exception e){
                                throw new InvalidInputException("Klarte ikke å lese tallet");
                           }
            break;
            case "ePost" : sokeResultatListe.addAll(collection.sokEpost(sokeOrd));
            break;
            case "Telefonnr" : sokeResultatListe.addAll(collection.sokTelefonnr(sokeOrd));
            break;
        }

        tableView.setItems(sokeResultatListe);

        if (sokeResultatListe.isEmpty()){
            throw new InvalidInputException("Ingen søkertreff");
        }
    }

    public void lastValg(ChoiceBox<String> filtrerSokCB) {
        sokeListe.removeAll();
        String valg1 = "Navn";
        String valg2 = "Alder";
        String valg3 = "ePost";
        String valg4 = "Telefonnr";
        sokeListe.addAll(valg1, valg2, valg3, valg4);
        filtrerSokCB.getItems().addAll(sokeListe);
        filtrerSokCB.setValue(valg1);
    }

    public void clearSokeResultatliste(){
        sokeResultatListe.clear();
    }

    public ObservableList<Person> getListe(){
        return this.sokeResultatListe;
    }


}
