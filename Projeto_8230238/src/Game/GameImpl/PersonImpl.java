/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Game.Interfaces.Division;
import Game.Interfaces.Person;

/**
 * @author Miguel Rocha
 */
public class PersonImpl implements Person {
    private String name;
    private Division division;
    
    /**
     * Constructor for Person class.
     * 
     * @param name the person name
     * @param division the division of the person
     */
    public PersonImpl(String name, Division division) {
        this.name = name;
        this.division = division;
    }
    
    /**
     * Return the name of the person.
     * 
     * @return person name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the position of the person.
     * 
     * @return person position
     */
    @Override
    public Division getDivision() {
        return division;
    }

    /**
     * Sets the division of the person.
     * 
     * @param division person division
     */
    @Override
    public void setDivision(Division division) {
        this.division = division;
    }
    
    /**
     * String representation of the person.
     * 
     * @return representation
     */
    @Override
    public String toString() {
        return "Name: " + name + " Divison: " + division;
    }
}
