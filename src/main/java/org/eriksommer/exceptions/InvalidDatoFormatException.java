package org.eriksommer.exceptions;

public class InvalidDatoFormatException extends IllegalArgumentException{
    public InvalidDatoFormatException(String msg){
        super(msg);
    }
}
