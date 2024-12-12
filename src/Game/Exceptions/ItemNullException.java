/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Exceptions;

/**
 *
 * @author Miguel
 */
public class ItemNullException extends RuntimeException {
    /**
     * Creates an ItemNullException with no message
     */
    public ItemNullException() {
        super();
    }

    /**
     * Creates an ItemNullException with the specified message
     *
     * @param message the message to be displayed with the exception
     */
    public ItemNullException(String message) {
        super(message);
    }  
}
