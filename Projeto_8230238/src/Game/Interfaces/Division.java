/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Collections.Lists.UnorderedListADT;

/**
 *
 * @author Miguel
 */
public interface Division {

    /**
     * Returns the name of the division.
     * 
     * @return division name
     */
    public String getName();
    
    /**
     * Sets the division name.
     * 
     * @param name name to set
     */
    public void setName(String name);
    
    /**
     * Return the persons in the division.
     * 
     * @return the persons in the division
     */
    public UnorderedListADT<Person> getPersonsInDivision();

    /**
     * Returns the item in the division.
     * 
     * @return the items in division
     */
    public UnorderedListADT<Item> getItemsInDivision();

    /**
     *
     * @return true if the division is a division that you can use as input or output
     */
    public boolean isEntranceExit();
}
