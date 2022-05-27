package com.unicomer.prueba.tecnica.shared.exception;
/**
 *
 *
 *
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-05
 */
public class ControllerException extends RuntimeException {


    private static final long serialVersionUID = 2925633469864942654L;

    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
