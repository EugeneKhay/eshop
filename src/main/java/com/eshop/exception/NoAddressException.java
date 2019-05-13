package com.eshop.exception;

public class NoAddressException extends RuntimeException {

    String message;

    public NoAddressException(String message) {
        super(message);
    }
}
