package com.unicomer.prueba.tecnica.shared.exception;
/**
 *
 *
 *
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-05
 */
public class ServiceException extends RuntimeException {


    private static final long serialVersionUID = 2925633469864942654L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    


}
