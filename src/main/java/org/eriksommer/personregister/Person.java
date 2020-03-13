package org.eriksommer.personregister;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {
    private transient SimpleStringProperty navn;
    private transient SimpleObjectProperty<Dato> dato;
    private transient int alder;
    private transient SimpleStringProperty telefonnr;
    private transient SimpleStringProperty epost;

    public Person(String navn, Dato dato, String telefonnr, String epost) {
        this.navn = new SimpleStringProperty(navn);
        this.dato = new SimpleObjectProperty<Dato>(dato);
        this.telefonnr = new SimpleStringProperty(telefonnr);
        this.epost = new SimpleStringProperty(epost);
        setAlder();
    }

    private void writeObject(ObjectOutputStream s) throws IOException{
        s.defaultWriteObject();
        s.writeUTF(navn.getValue());
        s.writeObject(dato.getValue());
        s.writeInt(alder);
        s.writeUTF(telefonnr.getValue());
        s.writeUTF(epost.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        String navn = s.readUTF();
        Dato dato = (Dato) s.readObject();
        int alder = s.readInt();
        String telefonnr = s.readUTF();
        String epost = s.readUTF();

        this.navn = new SimpleStringProperty(navn);
        this.dato = new SimpleObjectProperty<Dato>(dato);
        this.alder = alder;
        this.telefonnr = new SimpleStringProperty(telefonnr);
        this.epost = new SimpleStringProperty(epost);

    }

    public String getNavn() {
        return navn.getValue();
    }

    public void setNavn(String navn) {
        this.navn.set(navn);
    }

    //Returnerer en string slik at det er mulig å redigere i tabellen
    public String getDato() {
        return tilDato();
    }

    public void setDato(Dato dato) {
        this.dato.set(dato);
        setAlder();
    }

    public int getAlder(){
        return alder;
    }

    //Her er getMetoden til dato
    public Dato getDatoTilAlder(){
        return dato.getValue();
    }

    public void setAlder(){
        this.alder = Alder.finnAlder(getDatoTilAlder().getDag(),getDatoTilAlder().getManed(),getDatoTilAlder().getAr());
    }

    public void setNyAlderFraTabell(int nyAlder){
        this.alder = nyAlder;
    }

    public String getTelefonnr() {
        return telefonnr.getValue();
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr.set(telefonnr);
    }

    public String getEpost() {
        return epost.getValue();
    }

    public void setEpost(String epost) {
        this.epost.set(epost);
    }

    public String toFil() {
        return getNavn() + ";" + getDatoTilAlder().datoTilFil() + ";" + getTelefonnr() + ";" + getEpost();
    }

    //Hvordan datoen skrives ut i tabellen
    public String tilDato(){
        return getDatoTilAlder().getDag()+"."+getDatoTilAlder().getManed()+"."+getDatoTilAlder().getAr();
    }
}
