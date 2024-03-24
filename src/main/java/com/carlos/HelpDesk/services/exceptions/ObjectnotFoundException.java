package com.carlos.HelpDesk.services.exceptions;

/**
 * Esta classe representa uma exceção personalizada para quando um objeto não é encontrado.
 * Estende RuntimeException para indicar uma exceção não verificada.
 */
public class ObjectnotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Construtor que aceita uma mensagem e uma causa para a exceção.
     *
     * @param message A mensagem de erro associada à exceção.
     * @param cause A causa raiz da exceção.
     */
    public ObjectnotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Construtor que aceita apenas uma mensagem para a exceção.
     *
     * @param message A mensagem de erro associada à exceção.
     */
    public  ObjectnotFoundException (String message){
        super(message);
    }
}
