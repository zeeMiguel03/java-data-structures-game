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
     * Creates an ElementNotFoundException
     */
    public ElementNotFoundException() {
        super();
    }

    /**
     * Creates an ElementNotFoundException
     *
     * @param message the message
     */
    public ElementNotFoundException(String message) {
        super(message);
    }  
}
