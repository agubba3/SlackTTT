package com.ttt.Exceptions;

/**
 * Created by agubba on 10/12/16.
 */
public class IllegalMoveException extends Exception {
    public IllegalMoveException() { super(); }
    public IllegalMoveException(String message) { super(message); }
    public IllegalMoveException(String message, Throwable cause) { super(message, cause); }
    public IllegalMoveException(Throwable cause) { super(cause); }
}
