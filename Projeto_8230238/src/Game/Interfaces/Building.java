/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Collections.Graph.NetworkADT;
import Game.Exceptions.DivisionNullException;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public interface Building {
    
    /**
     * Return the divisions of the building.
     * 
     * @return the divisions
     */
    public NetworkADT<Division> getDivisions();
    
    /**
     * Sets the divisions of the building.
     * 
     * @param divisions the divisions
     */
    public void setDivisions(NetworkADT<Division> divisions);
    
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
}
