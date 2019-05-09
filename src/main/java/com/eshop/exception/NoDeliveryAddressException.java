package com.eshop.exception;

public class NoDeliveryAddressException extends RuntimeException {

    String message;

    public NoDeliveryAddressException(String message) {
        super(message);
    }
}
