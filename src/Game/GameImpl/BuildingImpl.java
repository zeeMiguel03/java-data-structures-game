/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Queues.LinkedQueue;
import Collections.Queues.QueueADT;
import Game.Exceptions.DivisionNullException;
import Game.Interfaces.*;

import java.util.Iterator;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class BuildingImpl implements Building {
    private GameNetworkADT<Division> divisions;
    QueueADT<Division> queueDivisions;
    private Division firstDivision;
    private Division itemDivision;

    /**
     * Constructor for building class.
     */
    public BuildingImpl() {
        this.divisions = new GameNetwork<>();
        this.queueDivisions = new LinkedQueue<>();
    }

    /**
     * Constructor for building class.
     *
     * @param divisions the building divisions
     */
    public BuildingImpl(GameNetworkADT<Division> divisions) {
        this.divisions = divisions;
    }

    /**
     * Returns the first division.
     *
     * @return the first division
     */
    @Override
    public Division getFirstDivision() {
        return firstDivision;
    }

    /**
     * Return the divisions of the building.
     *
     * @return the divisions
     */
    @Override
    public GameNetworkADT<Division> getDivisions() {
        return divisions;
    }

    /**
     * Sets the divisions of the building.
     *
     * @param divisions the divisions
     */
    @Override
    public void setDivisions(GameNetworkADT<Division> divisions) {
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

    @Override
    public QueueADT<Division> getEntranceExit() {

        if (queueDivisions.isEmpty()) {
            Iterator iterator = divisions.iteratorBFS(firstDivision);

            while (iterator.hasNext()) {
                Division currentDivision = (Division) iterator.next();

                if (currentDivision.getEntranceExit()) {
                    queueDivisions.enqueue(currentDivision);
                }
            }
        }
        
        return queueDivisions;
    }

    /**
     * This method print the divisions with adjacent to a specific division.
     *
     * @param division the division to search adjacent
     * @return 
     */
    @Override
    public QueueADT<Division> printNextDivisions(Division division) {
        int counter = 0;
        QueueADT<Division> queueDivisions = new LinkedQueue<>();
        
        Iterator<Division> iterator = divisions.iteratorAdjacent(division);

        System.out.println("\nConnect Divisions:");

        while (iterator.hasNext()) {
            Division currentDivision = iterator.next();
            System.out.println("[" + ++counter + "] " + currentDivision.getName());
            queueDivisions.enqueue(currentDivision);
        }
        
        return queueDivisions;
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

    @Override
    public void updateConnections(Player player) {
        Iterator<Division> iterator = divisions.iteratorBFS(firstDivision);

        while (iterator.hasNext()) {
            Division currentDivision = iterator.next();

            Iterator<Division> iterator2 = divisions.iteratorAdjacent(currentDivision);
            while (iterator2.hasNext()) {
                Division currentDivision2 = iterator2.next();

                removeConnection(currentDivision, currentDivision2);

                int weight1To2 = 0;

                if (!currentDivision2.getEnemysInDivision().isEmpty()) {
                    for (Enemy enemy : currentDivision2.getEnemysInDivision()) {
                        if (player.getPower() > 0) {
                            weight1To2 += (int) (enemy.getPower() * (Math.ceil((float)enemy.getLife() / player.getPower()) - 1));

                        } else {
                            weight1To2 += player.getMaxLife();

                        }
                    }
                }

                addConection(currentDivision, currentDivision2, weight1To2);
            }
        }
    }
    
    @Override
    public Division getItemDivision() {
        Iterator<Division> iterator = divisions.iteratorBFS(firstDivision);
        
        while (iterator.hasNext()) {
            Division division = iterator.next();
            
            if (division.getTarget() != null) {
                return division;
            }   
        }
        
        return null;
    }
}

