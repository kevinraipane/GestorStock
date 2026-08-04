package com.example.GestorStock.common.globalHandler.customException;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
