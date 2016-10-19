package com.ttt.Exceptions;

/**
 * Created by agubba on 10/12/16.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() { super(); }
    public InvalidCommandException(String message) { super(message); }
    public InvalidCommandException(String message, Throwable cause) { super(message, cause); }
    public InvalidCommandException(Throwable cause) { super(cause); }
}
