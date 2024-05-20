package com.django.it.shoppingapp.exception;

public class OperationNotPermittedException extends RuntimeException {

    OperationNotPermittedException() {}
    OperationNotPermittedException(String message) {
        super(message);
    }
}
