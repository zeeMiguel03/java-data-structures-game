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
 * @author Miguel Rocha
 * @author AntÃ³nio Monteiro
 */
public interface Building {

    /**
     * Return the divisions of the building.
     *
     * @return the divisions
     */
    public GameNetworkADT<Division> getDivisions();

    /**
     * Sets the divisions of the building.
     *
     * @param divisions the divisions
     */
    public void setDivisions(GameNetworkADT<Division> divisions);

    /**
     * Returns the first division.
     *
     * @return the first division
     */
    public Division getFirstDivision();

    /**
     * Adds a new division to the building.
     *
     * @param division the division to add
     */
    public void addDivision(Division division) throws DivisionNullException;

    /**
     * Removes a division of the building.
     *
     * @param division the division to remove
     */
    public void removeDivision(Division division) throws DivisionNullException;

    /**
     * Adds a connection between two divisions.
     *
     * @param division1 the first division
     * @param division2 the second division
     * @throws DivisionNullException if one of the divisions is null
     */
    public void addConection(Division division1, Division division2) throws DivisionNullException;

    /**
     * Adds a connection between two divisions with a specific weight.
     *
     * @param division1 the first division
     * @param division2 the second division
     * @param weight the size of the connection
     * @throws DivisionNullException if one of the divisions is null
     */
    public void addConection(Division division1, Division division2, int weight) throws DivisionNullException;

    /**
     * Removes a connections between two divisions.
     *
     * @param division1 the first division
     * @param division2 the second division
     * @throws DivisionNullException if one of the divisions is null
     */
    public void removeConnection(Division division1, Division division2) throws DivisionNullException;

    /**
     * This method print the divisions that are a entrance or a exit.
     * 
     * @return the queue of divisions.
     */
    public QueueADT<Division> printEntranceExit();

    /**
     * This method print the divisions with adjacent to a specific division.
     *
     * @param division the division to search adjacent
     */
    public QueueADT<Division> printNextDivisions(Division division);

    /**
     * Searchs for a specific division.
     *
     * @param name the division name to search for
     * @return the division if it was found
     * @throws ElementNotFoundException if the division was not found
     */
    public Division searchDivisionByName(String name) throws ElementNotFoundException;
}