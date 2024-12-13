/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Exceptions;

/**
 * Custom exception to handle item null errors in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class ItemNullException extends RuntimeException {

    /**
     * Creates an ItemNullException.
     *
     * @param message the message to show
     */
    public ItemNullException(String message) {
        super(message);
    }  
}
