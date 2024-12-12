/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Exceptions;

/**
 *
 * @author Miguel
 */
public class ElementNotFoundException extends RuntimeException{
    /**
     * Creates an ElementNotFoundException with no message
     */
    public ElementNotFoundException() {
        super();
    }

    /**
     * Creates an ElementNotFoundException with the specified message
     *
     * @param message the message to be displayed with the exception
     */
    public ElementNotFoundException(String message) {
        super(message);
    }  
}
