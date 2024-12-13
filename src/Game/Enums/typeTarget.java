/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Enums;

/**
 * Enumeration representing the types of targets available in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public enum typeTarget {
    CHEMICAL, PERSON, GUN;

    /**
     * String representation of the target
     *
     * @return the target representation
     */
    @Override
    public String toString() {
        switch(this) {
            case CHEMICAL:
                return "Químico";
            case PERSON:
                return "Pessoa";
            case GUN:
                return "Arma";
            default:
                throw new AssertionError();
        }
    }
}
