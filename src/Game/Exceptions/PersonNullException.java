/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Exceptions;

/**
 * Custom exception to handle Person null errors in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class PersonNullException extends RuntimeException {

    /**
     * Creates an PersonNullException.
     *
     * @param message the message to show
     */
    public PersonNullException(String message) {
        super(message);
    }  
}
