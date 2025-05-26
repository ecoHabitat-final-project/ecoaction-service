package com.ecohabitat.ecoaction_service.exceptions;

public class EcoactionsNotFoundException extends RuntimeException {
    public EcoactionsNotFoundException(String message) {
        super(message);
    }
}
