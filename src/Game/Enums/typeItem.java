/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.Enums;

/**
 * Enumeration representing the types of items available in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public enum typeItem {
    KIT_LIFE, VEST;

    /**
     * String representation of the item
     *
     * @return the item representation
     */
    @Override
    public String toString() {
        switch(this) {
            case KIT_LIFE:
                return "kit de vida";
            case VEST:
                return "colete";
            default:
                throw new AssertionError();
        }
    }
}
