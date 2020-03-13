package org.eriksommer.exceptions;

public class InvalidInputException extends IllegalArgumentException{
    public InvalidInputException(String msg){
        super(msg);
    }
}
