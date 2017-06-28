package com.saplacan.model;

/**
 * Exception thrown for Invalid Cube input in constructor.
 */
public class InvalidCubeException extends Exception {
    public InvalidCubeException(String message) {
        super(message);
    }
}
