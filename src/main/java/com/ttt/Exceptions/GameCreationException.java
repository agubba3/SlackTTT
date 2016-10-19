package com.ttt.Exceptions;

/**
 * Created by agubba on 10/12/16.
 */
public class GameCreationException extends Exception {
    public GameCreationException() { super(); }
    public GameCreationException(String message) { super(message); }
    public GameCreationException(String message, Throwable cause) { super(message, cause); }
    public GameCreationException(Throwable cause) { super(cause); }
}
