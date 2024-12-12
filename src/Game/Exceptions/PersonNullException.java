/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Exceptions;

/**
 *
 * @author Miguel
 */
public class PersonNullException extends RuntimeException {
    /**
     * Creates an PersonNullException with no message
     */
    public PersonNullException() {
        super();
    }

    /**
     * Creates an PersonNullException with the specified message
     *
     * @param message the message to be displayed with the exception
     */
    public PersonNullException(String message) {
        super(message);
    }  
}
