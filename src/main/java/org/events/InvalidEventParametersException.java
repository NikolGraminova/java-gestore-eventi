package org.events;

public class InvalidEventParametersException extends IllegalArgumentException{
    public InvalidEventParametersException(String s) {
        super(s);
    }
}
