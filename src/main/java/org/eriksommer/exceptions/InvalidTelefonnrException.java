package org.eriksommer.exceptions;

public class InvalidTelefonnrException extends IllegalArgumentException {
    public InvalidTelefonnrException(String msg){
        super(msg);
    }
}
