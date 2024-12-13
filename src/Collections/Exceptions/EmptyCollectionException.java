/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Exceptions;

/**
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class EmptyCollectionException extends RuntimeException {

    /**
     * Creates an EmptyCollectionException
     */
    public EmptyCollectionException() {
        super();
    }

    /**
     * Creates an EmptyCollectionException
     *
     * @param message the message
     */
    public EmptyCollectionException(String message) {
        super(message);
    }  
}
