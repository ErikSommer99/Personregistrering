package org.eriksommer.exceptions;

public class InvalidDatoException extends IllegalArgumentException {
    public InvalidDatoException(String msg){
        super(msg);
    }
}
