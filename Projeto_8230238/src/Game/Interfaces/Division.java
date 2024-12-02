/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Collections.Lists.UnorderedListADT;
import Collections.Stacks.StackADT;

/**
 *
 * @author Miguel
 */
public interface Division {

    /**
     *
     * @return a stack with the persons that are in division
     */
    public UnorderedListADT<Person> getPersonsInDivision();

    /**
     *
     * @return the divisions the person can go through this divion
     */
    public UnorderedListADT<Division> getAdjacentDivisions();

    /**
     *
     * @return the items in division
     */
    public UnorderedListADT<Item> getItemsInDivision();
}
