package org.eriksommer.personregister;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataCollection {

    private transient ObservableList<Person> liste = FXCollections.observableArrayList();

    public DataCollection(ObservableList<Person> liste) {
        this.liste = liste;
    }

    public DataCollection() {

    }

    public void writeObject(ObjectOutputStream s) throws IOException, ClassNotFoundException{
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(liste));
    }

    @SuppressWarnings("unchecked")
    public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        List<Person> personListe = (List<Person>) s.readObject();
        liste = FXCollections.observableArrayList();
        liste.addAll(personListe);
    }

    public void leggTil(ObservableList<Person> enPersonListe){
        liste = enPersonListe;
    }

    public ObservableList<Person> getListe(){
        return this.liste;
    }

    public void leggTil(Person enPerson){
        liste.add(enPerson);
    }

    public void nullstill(){
        liste.clear();
    }

    //Søkefunksjon med streams
    public ObservableList<Person> sokNavn(String verdi){

        return liste.stream().filter(enPerson -> enPerson.getNavn().toLowerCase().contains(verdi.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Person> sokAlder(int verdi){

        return liste.stream().filter(enPerson -> enPerson.getAlder()==(verdi)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Person> sokEpost(String verdi){

        return liste.stream().filter(enPerson -> enPerson.getEpost().contains(verdi)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Person> sokTelefonnr(String verdi){

        return liste.stream().filter(enPerson -> enPerson.getTelefonnr().contains(verdi)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public String utTxtFil() {
        StringBuilder ut = new StringBuilder();
        for(Person enPerson : liste){
            ut.append(enPerson.toFil()).append("\n");
        }
        return ut.toString();
    }

    @Override
    public String toString(){
        StringBuilder melding = new StringBuilder();
        for (Person enPerson : liste){
            melding.append("Navn: ").append(enPerson.getNavn()).
                    append(", født ").append(enPerson.getDato()).
                    append(", alder: ").append(enPerson.getAlder()).
                    append(", telefonnr: ").append(enPerson.getTelefonnr()).
                    append(", epost: ").
                    append(enPerson.getEpost());
        }
        return melding.toString();
    }
}
