package com.unicomer.prueba.tecnica.shared.exception;
/**
 *
 *
 *
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-05
 */
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5229299037271353333L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
   
}
