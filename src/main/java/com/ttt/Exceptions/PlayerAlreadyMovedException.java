package com.ttt.Exceptions;

/**
 * Created by agubba on 10/12/16.
 */
public class PlayerAlreadyMovedException extends Exception {
    public PlayerAlreadyMovedException() { super(); }
    public PlayerAlreadyMovedException(String message) { super(message); }
    public PlayerAlreadyMovedException(String message, Throwable cause) { super(message, cause); }
    public PlayerAlreadyMovedException(Throwable cause) { super(cause); }
}