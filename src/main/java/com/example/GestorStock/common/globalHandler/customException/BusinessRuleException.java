package com.example.GestorStock.common.globalHandler.customException;

public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
