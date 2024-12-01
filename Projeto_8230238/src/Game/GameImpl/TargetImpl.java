/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Game.Enums.typeTarget;
import Game.Interfaces.Division;
import Game.Interfaces.Target;

/**
 * @author Miguel Rocha
 */
public class TargetImpl implements Target {
    private typeTarget type;
    private Division division;
    
    /**
     * Constructor for Target class.
     * 
     * @param type the type of the target
     * @param division the division of the target
     */
    public TargetImpl(typeTarget type, Division division) {
        this.type = type;
        this.division = division;
    }

    /**
     * Return the type of the Target.
     * 
     * @return target type
     */
    @Override
    public typeTarget getType() {
        return type;
    }

    /**
     * Sets the type of the item.
     * 
     * @param type item to set
     */
    @Override
    public void setType(typeTarget type) {
        this.type = type;
    }

    /**
     * Return the division of the target.
     * 
     * @return the division
     */
    @Override
    public Division getDivision() {
        return division;
    }

    /**
     * Sets the division of the target.
     * 
     * @param division the division to set
     */
    @Override
    public void setDivision(Division division) {
        this.division = division;
    }
    
    /**
     * String representation of the target.
     * 
     * @return representation
     */
    @Override
    public String toString() {
        return "Type: " + type + " Division: " + division;
    }
}
