/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Exceptions;

/**
 *
 * @author Miguel
 */
public class InvalidEntranceException extends RuntimeException {
    /**
     * Creates an DivisionNullException with no message
     */
    public InvalidEntranceException() {
        super();
    }

    /**
     * Creates an DivisionNullException with the specified message
     *
     * @param message the message to be displayed with the exception
     */
    public InvalidEntranceException(String message) {
        super(message);
    }  
}
