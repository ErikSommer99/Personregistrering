package org.eriksommer.gui;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.eriksommer.exceptions.*;
import org.eriksommer.personregister.DataCollection;
import org.eriksommer.personregister.Dato;
import org.eriksommer.personregister.Person;
import org.eriksommer.personregister.PersonValidator;


public class PrimaryController implements Initializable{

    RegistrerPerson registrerPerson = new RegistrerPerson();
    DataCollection collection = new DataCollection();
    Search newSearch = new Search();

    @FXML
    private TextField txtNavn;

    @FXML
    private TextField txtDag, txtManed, txtAr, txtTelefonnr, txtEpost, txtSok;

    @FXML
    private Label lblMelding, lblMeldingSok;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, Integer> txtAlderListe;

    @FXML
    private ChoiceBox<String> filtrerSokCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtAlderListe.setCellValueFactory(new PropertyValueFactory<Person, Integer>("alder"));
        newSearch.lastValg(filtrerSokCB);
    }

    @FXML
    void btnFjernFil(ActionEvent event) {
        tomMelding();
        collection.nullstill();
        newSearch.clearSokeResultatliste();
    }

    @FXML
    void btnLastFil(ActionEvent event) {
        tomMelding();
        Filbehandler.lastFil(collection, tableView, lblMelding);
    }

    @FXML
    void btnLagre(ActionEvent event) throws IOException, ClassNotFoundException {
        tomMelding();
        Filbehandler.lagreFil(collection, newSearch, tableView, lblMelding);
    }

    @FXML
    void btnRegistrer(ActionEvent event) throws IOException {
        tomMelding();
        Person objekt = registrerPerson.opprettObjekt(txtNavn.getText(),txtDag.getText(),txtManed.getText(),
                txtAr.getText(), txtTelefonnr.getText(), txtEpost.getText());

            if (objekt != null){
                collection.leggTil(objekt);
                tableView.setItems(collection.getListe());
                lblMelding.setText("Registrert");
                tomInputfelt();
            }
        }

    @FXML
    void btnSok(ActionEvent event) {
        tomMelding();
        String sokeVerdi = filtrerSokCB.getValue();
        String sokeOrd = txtSok.getText();
        try {
            newSearch.search(collection, sokeVerdi, sokeOrd, tableView);
        }catch (InvalidInputException e){
            lblMeldingSok.setText(e.getMessage());
        }
    }

    @FXML
    void btnFjernSok(ActionEvent event) {
        txtSok.clear();
        tomMelding();
        if (tableView.getItems() == newSearch.getListe()){
            newSearch.clearSokeResultatliste();
            tableView.setItems(collection.getListe());
        }else {
            lblMeldingSok.setText("Du har ikke gjennomført et søk");
        }
    }

    @FXML
    void editDato(TableColumn.CellEditEvent<Person, String> event) {
        tomMelding();
        try{
            event.getRowValue().setDato(PersonValidator.testValidDato(Dato.parseDato(event.getNewValue())));
            tableView.refresh();
        }catch (InvalidDatoFormatException | InvalidDatoException | InvalidInputException | InvalidAgeException e){
            Dialogs.showErrorDialog("Ugyldig dato!", e.getMessage());
            tableView.refresh();
        }
    }

    @FXML
    void editEpost(TableColumn.CellEditEvent<Person, String> event) {
        tomMelding();
        try{
            event.getRowValue().setEpost(PersonValidator.testValidEpost(event.getNewValue()));
        }catch (InvalidEpostException e){
            Dialogs.showErrorDialog("Ugyldig epost!", e.getMessage());
            tableView.refresh();
        }
    }

    @FXML
    void editNavn(TableColumn.CellEditEvent<Person, String> event) {
        tomMelding();
        try{
            event.getRowValue().setNavn(PersonValidator.testValidNavn(event.getNewValue()));
        }catch (InvalidNameException e){
            Dialogs.showErrorDialog("Ugyldig navn!", e.getMessage());
            tableView.refresh();
        }
    }

    @FXML
    void editTelefonnr(TableColumn.CellEditEvent<Person, String> event) {
        tomMelding();
        try{
            event.getRowValue().setTelefonnr(PersonValidator.testValidTelefon(event.getNewValue()));
        }catch (InvalidTelefonnrException e){
            Dialogs.showErrorDialog("Ugyldig telefonnummer!", e.getMessage());
            tableView.refresh();
        }
    }

    @FXML
    void editAlder(TableColumn.CellEditEvent<Person, Integer> event) {
        Dialogs.showErrorDialog("Du kan ikke redigere alderen", "Du må endre på datoen");
    }

    public void tomInputfelt(){
        txtNavn.clear();
        txtDag.clear();
        txtManed.clear();
        txtAr.clear();
        txtTelefonnr.clear();
        txtEpost.clear();
    }

    public void tomMelding(){
        lblMelding.setText("");
        lblMeldingSok.setText("");
    }
}