/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Graph.Network;
import Collections.Graph.NetworkADT;
import Game.Exceptions.DivisionNullException;
import Game.Interfaces.Building;
import Game.Interfaces.Division;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class BuildingImpl implements Building {
    private NetworkADT<Division> divisions;
    
    /**
     * Constructor for building class.
     */
    public BuildingImpl() {
        this.divisions = new Network<>();
    }
    
    /**
     * Constructor for building class.
     * 
     * @param divisions the building divisions
     */
    public BuildingImpl(NetworkADT<Division> divisions) {
        this.divisions = divisions;
    }
    
    /**
     * Return the divisions of the building.
     * 
     * @return the divisions
     */
    @Override
    public NetworkADT<Division> getDivisions() {
        return divisions;
    }
    
    /**
     * Sets the divisions of the building.
     * 
     * @param divisions the divisions
     */
    @Override
    public void setDivisions(NetworkADT<Division> divisions) {
        this.divisions = divisions;
    }
    
    /**
     * Adds a new division to the building.
     * 
     * @param division the division to add
     * @throws DivisionNullException if the division is null
     */
    @Override
    public void addDivision(Division division) throws DivisionNullException {
        if (division == null) {
            throw new DivisionNullException("Division null!");
        }
        
        divisions.addVertex(division);
    }
    
    /**
     * Removes a division of the building.
     * 
     * @param division the division to remove
     * @throws DivisionNullException if the division is null
     */
    @Override
    public void removeDivision(Division division) throws DivisionNullException {
        if (division == null) {
            throw new DivisionNullException("Division null!");
        }
        
        divisions.removeVertex(division);
    }
    
    /**
     * Adds a connection between two divisions.
     * 
     * @param division1 the first division
     * @param division2 the second division
     * @throws DivisionNullException if one of the divisions is null
     */
    @Override
    public void addConection(Division division1, Division division2) throws DivisionNullException {
        if (division1 == null || division2 == null) {
            throw new DivisionNullException("Division null!");
        }
        
        divisions.addEdge(division1, division2);
    }
    
    /**
     * Adds a connection between two divisions.
     * 
     * @param division1 the first division
     * @param division2 the second division
     * @param weight the size of the connection
     * @throws DivisionNullException if one of the divisions is null
     */
    @Override
    public void addConection(Division division1, Division division2, int weight) throws DivisionNullException {
        if (division1 == null || division2 == null) {
            throw new DivisionNullException("One of the divisions is null!");
        }
        
        divisions.addEdge(division1, division2, weight);
    }
    
    /**
     * Removes a connections between two divisions.
     * 
     * @param division1 the first division
     * @param division2 the second division
     * @throws DivisionNullException if one of the divisions is null
     */
    @Override
    public void removeConnection(Division division1, Division division2) throws DivisionNullException {
        if (division1 == null || division2 == null) {
            throw new DivisionNullException("One of the divisions is null!");
        }
        
        divisions.removeEdge(division1, division2);
    }
}
