package com.ecohabitat.ecoaction_service.exceptions;

public class EcoactionNotFoundException extends RuntimeException {
    public EcoactionNotFoundException(String message) {
        super(message);
    }
}
