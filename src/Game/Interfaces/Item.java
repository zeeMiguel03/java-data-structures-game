/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Game.Enums.typeItem;

/**
 * Implementation of the Item interface, representing a item in the game. *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Item {  
    
    /**
     * Returns the item division.
     * 
     * @return the item division
     */
    Division getDivision();
    
    /**
     * Sets the item division.
     * 
     * @param division the division to set
     */
    void setDivision(Division division);
    
    /**
     * Return the point of the item.
     * 
     * @return the points of the item
     */
    int getPoints();
    
    /**
     * Sets the points of the item
     * 
     * @param points the points to set
     */
    void setPoints(int points);
    
    /**
     * Sets the type of the item.
     * 
     * @param type item type.
     */
    void setType(typeItem type);
    
    /**
     * Return the type of the item.
     * 
     * @return type of the item
     */
    typeItem getType();
}
