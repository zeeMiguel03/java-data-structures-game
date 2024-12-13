/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Game.Enums.typeItem;
import Game.Interfaces.Division;
import Game.Interfaces.Item;

/**
 * Implementation of the item interface, representing a item in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class ItemImpl implements Item {
    private typeItem type;
    private int points;
    private Division division;
    
    /**
     * Constructor for the item class.
     * 
     * @param type type of the item
     * @param points points of the item
     * @param division divison of the item
     */
    public ItemImpl(typeItem type, int points, Division division) {
        this.type = type;
        this.points = points;
        this.division = division;
    }

    /**
     * Returns the item division.
     * 
     * @return the item division
     */
    @Override
    public Division getDivision() {
        return division;
    }

    /**
     * Sets the item division.
     * 
     * @param division the division to set
     */
    @Override
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * Return the point of the item.
     * 
     * @return the points of the item
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of the item
     * 
     * @param points the points to set
     */
    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Sets the type of the item.
     * 
     * @param type item type.
     */
    @Override
    public void setType(typeItem type) {
        this.type = type;
    }

    /**
     * Return the type of the item.
     * 
     * @return type of the item
     */
    @Override
    public typeItem getType() {
        return type;
    }
    
    /**
     * Return a string representation of the item.
     * 
     * @return the string representation 
     */
    @Override
    public String toString() {
        return "Type: " + type + " Points:" + points + " Division: " + division;
    }
}
