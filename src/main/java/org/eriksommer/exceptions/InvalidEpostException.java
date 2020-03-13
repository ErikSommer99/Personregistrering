package org.eriksommer.exceptions;

public class InvalidEpostException extends IllegalArgumentException {
    public InvalidEpostException(String msg) {
        super(msg);
    }
}
