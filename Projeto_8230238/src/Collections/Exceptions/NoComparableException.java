/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Exceptions;

/**
 *
 * @author Miguel
 */
public class NoComparableException extends RuntimeException {
    /**
     * Creates an NoComparableException with no message
     */
    public NoComparableException() {
        super();
    }

    /**
     * Creates an NoComparableException with the specified message
     *
     * @param message the message to be displayed with the exception
     */
    public NoComparableException(String message) {
        super(message);
    }  
}
