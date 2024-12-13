/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Game.Enums.typeTarget;

/**
 *  A interface representing of the item in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Target {

    /**
     * Return the type of the Target.
     * 
     * @return target type
     */
    typeTarget getType();
    
    /**
     * Sets the type of the item.
     * 
     * @param type item to set
     */
    void setType(typeTarget type);
    
    /**
     * Return the division of the target.
     * 
     * @return the division
     */
    Division getDivision();
    
    /**
     * Sets the division of the target.
     * 
     * @param division the division to set
     */
    void setDivision(Division division);
}
