/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Game.Enums.typeItem;

/**
 * Implementation of the Item interface, representing a item in the game.
 * 
 * @author Miguel Rocha
 * @author António Monteiro
 */
public interface Item {  
    
    /**
     * Returns the item division.
     * 
     * @return the item division
     */
    public Division getDivision();
    
    /**
     * Sets the item division.
     * 
     * @param division the division to set
     */
    public void setDivision(Division division);
    
    /**
     * Return the point of the item.
     * 
     * @return the points of the item
     */
    public int getPoints();
    
    /**
     * Sets the points of the item
     * 
     * @param points the points to set
     */
    public void setPoints(int points);
    
    /**
     * Sets the type of the item.
     * 
     * @param type item type.
     */
    public void setType(typeItem type);
    
    /**
     * Return the type of the item.
     * 
     * @return type of the item
     */
    public typeItem getType();
}
