/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Miguel
 */
public class EmptyCollectionException extends RuntimeException {
    /**
     * Creates an EmptyCollectionException with no message
     */
    public EmptyCollectionException() {
        super();
    }

    /**
     * Creates an EmptyCollectionException with the specified message
     *
     * @param message the message to be displayed with the exception
     */
    public EmptyCollectionException(String message) {
        super(message);
    }  
}
