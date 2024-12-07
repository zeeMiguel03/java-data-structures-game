/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Graph.Network;
import Collections.Graph.NetworkADT;
import Game.Exceptions.DivisionNullException;
import Game.Interfaces.Building;
import Game.Interfaces.Division;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class BuildingImpl implements Building {
    private NetworkADT<Division> divisions;
    private Division firstDivision;
    
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
     * Adds a new division to the building, and if the divisions is empty adds
     * the firstDivision.
     * 
     * @param division the division to add
     * @throws DivisionNullException if the division is null
     */
    @Override
    public void addDivision(Division division) throws DivisionNullException {
        if (division == null) {
            throw new DivisionNullException("Division null!");
        }
        
        if (divisions.isEmpty()) {
            firstDivision = division;
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

    public Division getFirstDivision() {
        return firstDivision;
    }
    
    /**
     * This method print the divisions that are a entrance or a exit.
     */
    @Override
    public void printEntranceExit() {
        Iterator<Division> iterator = divisions.iteratorBFS(firstDivision);
        
        System.out.println("\nEntrace or exit Divisions:");
        
        while (iterator.hasNext()) {
            Division currentDivision = iterator.next();
            
            if (currentDivision.getEntranceExit()) {
                System.out.print(currentDivision.getName() + " " + "\n");
            }
        }
    }
    
    /**
     * Searchs for a specific division.
     * 
     * @param name the division name to search for
     * @return the division if it was found
     * @throws ElementNotFoundException if the division was not found
     */
    @Override
    public Division searchDivisionByName(String name) throws ElementNotFoundException {
        Iterator<Division> iterator = divisions.iteratorBFS(firstDivision);
        
        while (iterator.hasNext()) {
            Division currentDivision = iterator.next();
            
            if (currentDivision.getName().equals(name)) {
                return currentDivision;
            }
        } 

        throw new ElementNotFoundException("Division with name " + name + " not found.");
    }
    
    //Perguntar ao stor se podemos criar a nossa própia network e criar por exemplo este method de cima(nao esquecer)
}
