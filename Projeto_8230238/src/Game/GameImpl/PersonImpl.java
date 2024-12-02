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
    private int power;
    
    /**
     * Constructor for Person class.
     * 
     * @param name the person name
     * @param division the division of the person
     * @param power the person power
     */
    public PersonImpl(String name, Division division, int power) {
        this.name = name;
        this.division = division;
        this.power = power;
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
     * Returns the power of the person.
     * 
     * @return person power
     */
    @Override
    public int getPower() {
        return power;
    }

    /**
     * Sets the power of the person.
     * 
     * @param power sets the power
     */
    @Override
    public void setPower(int power) { 
        this.power = power;
    }
    
    /**
     * String representation of the person.
     * 
     * @return representation
     */
    @Override
    public String toString() {
        return "Name: " + name + " Divison: " + division + "Power: " + power;
    }
}
