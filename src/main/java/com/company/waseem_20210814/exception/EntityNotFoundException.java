package com.company.waseem_20210814.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String entityType) {
        super(entityType + " not found");
    }
}
