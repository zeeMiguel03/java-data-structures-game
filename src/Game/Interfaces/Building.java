/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import Collections.Exceptions.ElementNotFoundException;
import Collections.Queues.QueueADT;
import Game.Exceptions.DivisionNullException;

/**
 *  A interface representing of the building in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Building {

    /**
     * Return the divisions of the building.
     *
     * @return the divisions
     */
    GameNetworkADT<Division> getDivisions();

    /**
     * Sets the divisions of the building.
     *
     * @param divisions the divisions
     */
    void setDivisions(GameNetworkADT<Division> divisions);

    /**
     * Returns the first division.
     *
     * @return the first division
     */
    Division getFirstDivision();

    /**
     * Adds a new division to the building.
     *
     * @param division the division to add
     */
    void addDivision(Division division) throws DivisionNullException;

    /**
     * Removes a division of the building.
     *
     * @param division the division to remove
     */
    void removeDivision(Division division) throws DivisionNullException;

    /**
     * Adds a connection between two divisions.
     *
     * @param division1 the first division
     * @param division2 the second division
     * @throws DivisionNullException if one of the divisions is null
     */
    void addConection(Division division1, Division division2) throws DivisionNullException;

    /**
     * Adds a connection between two divisions with a specific weight.
     *
     * @param division1 the first division
     * @param division2 the second division
     * @param weight the size of the connection
     * @throws DivisionNullException if one of the divisions is null
     */
    void addConection(Division division1, Division division2, int weight) throws DivisionNullException;

    /**
     * Removes a connections between two divisions.
     *
     * @param division1 the first division
     * @param division2 the second division
     * @throws DivisionNullException if one of the divisions is null
     */
    void removeConnection(Division division1, Division division2) throws DivisionNullException;

    /**
     * This method print the divisions who are entrance or exit.
     *
     * @return a queue with all the entrance or exit
     */
    QueueADT<Division> getEntranceExit();

    /**
     * This method print the divisions with adjacent to a specific division.
     *
     * @param division the division to search adjacent
     * @return a queue with all the next possible divisions
     */
    QueueADT<Division> printNextDivisions(Division division);

    /**
     * Search for a specific division, by the name.
     *
     * @param name the division name to search for
     * @return the division if it was found
     * @throws ElementNotFoundException if the division was not found
     */
     Division searchDivisionByName(String name) throws ElementNotFoundException;

    /**
     * Updates the connections between divisions based on the player power.
     *
     * @param player the player whose power is used to update the connections
     */
     void updateConnections(Player player);
}