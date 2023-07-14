package com.aquakloud.ECommerce.exceptions;

public class ProductNoExistsException extends IllegalArgumentException {
    public ProductNoExistsException(String msg) {
        super(msg);
    }
}
