package org.eriksommer.personregister;

import javafx.beans.property.SimpleIntegerProperty;
import org.eriksommer.io.FileOpenerTxt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Dato implements Serializable {
    private transient SimpleIntegerProperty dag;
    private transient SimpleIntegerProperty maned;
    private transient SimpleIntegerProperty ar;

    public Dato(int dag, int maned, int ar) {
        this.dag = new SimpleIntegerProperty(dag);
        this.maned = new SimpleIntegerProperty(maned);
        this.ar = new SimpleIntegerProperty(ar);
    }

    private void writeObject(ObjectOutputStream s) throws IOException{
        s.defaultWriteObject();
        s.writeInt(dag.getValue());
        s.writeInt(maned.getValue());
        s.writeInt(ar.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        int dag = s.readInt();
        int maned = s.readInt();
        int ar = s.readInt();

        this.dag = new SimpleIntegerProperty(dag);
        this.maned = new SimpleIntegerProperty(maned);
        this.ar = new SimpleIntegerProperty(ar);
    }

    public int getDag() {
        return dag.getValue();
    }

    public void setDag(int dag) {
        this.dag.set(dag);
    }

    public int getManed() {
        return maned.getValue();
    }

    public void setManed(int maned) {
        this.maned.set(maned);
    }

    public int getAr() {
        return ar.getValue();
    }

    public void setAr(int ar) {
        this.ar.set(ar);
    }

    public static Dato parseDato(String dato) {
        return FileOpenerTxt.parseDato(dato);
    }

    public String datoTilFil() {
        return getDag() + ";" + getManed() + ";" + getAr();
    }
}
