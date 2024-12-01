/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Game.Enums.typeTarget;

/**
 * @author Miguel Rocha
 */
public interface Target {
    /**
     * Return the type of the Target.
     * 
     * @return target type
     */
    public typeTarget getType();
    
    /**
     * Sets the type of the item.
     * 
     * @param type item to set
     */
    public void setType(typeTarget type);
    
    /**
     * Return the division of the target.
     * 
     * @return the division
     */
    public Division getDivision();
    
    /**
     * Sets the division of the target.
     * 
     * @param division the division to set
     */
    public void setDivision(Division division);
}
