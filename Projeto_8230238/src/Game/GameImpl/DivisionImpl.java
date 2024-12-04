/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Exceptions.ItemNullException;
import Game.Exceptions.PersonNullException;
import Game.Interfaces.Division;
import Game.Interfaces.Item;
import Game.Interfaces.Person;

/**
 * Implementation of the Division interface, representing a division in the game.
 * 
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class DivisionImpl implements Division {
    private static final int MAX_ITENS = 2;
    
    private String name;
    private UnorderedListADT<Person> persons;
    private UnorderedListADT<Item> itens;
    private boolean entranceExit;
    
    /**
     * Constructor for the division class.
     * 
     * @param name the name of the division
     * @param entranceExit true if is a exit or entrance
     */
    public DivisionImpl(String name, boolean entranceExit) {
        this.name = name;
        this.entranceExit = entranceExit;
        this.persons = new LinkedUnorderedList<>();
        this.itens = new LinkedUnorderedList<>();
    }

    /**
     * Returns the name of the division.
     * 
     * @return division name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the division name.
     * 
     * @param name name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns if the division is a entrance or a exit.
     * 
     * @return if the division is a entrance or a exit
     */
    @Override
    public boolean getEntranceExit() {
        return entranceExit;
    }
    
    /**
     * Sets if the division is a entrance or a exit.
     * 
     * @param entranceExit option to set
     */
    @Override
    public void setEntranceExit(boolean entranceExit) {
        this.entranceExit = entranceExit;
    }
    
    /**
     * Return the persons in the division.
     * 
     * @return the persons in the division
     */
    @Override
    public UnorderedListADT<Person> getPersonsInDivision() {
        return persons;
    }
    
    /**
     * Sets the persons in the division.
     * 
     * @param persons persons to set
     */
    @Override
    public void setPersonsInDivision(UnorderedListADT<Person> persons) {
        this.persons = persons;
    }

    /**
     * Returns the item in the division.
     * 
     * @return the items in division
     */
    @Override
    public UnorderedListADT<Item> getItemsInDivision() {
        return itens;
    }
    
    /**
     * Sets the itens in the division.
     * 
     * @param itens itens to set
     */
    @Override
    public void setItensInDivision(UnorderedListADT<Item> itens) {
        this.itens = itens; //exception talvez, porque esse itens pode ter mais de dois!!
    }

    /**
     * Return true if the division is a division that you can use as input or output.
     * 
     * @return true if the division is a division that you can use as input or output
     */
    @Override
    public boolean isEntranceExit() {
        return entranceExit;
    }
    
    /**
     * Adds a new person to the division.
     * 
     * @param person person to add in division
     * @throws PersonNullException if the person in null
     */
    @Override
    public void addPerson(Person person) throws PersonNullException {
        if (person == null) {
            throw new PersonNullException("Person is null!");
        }
        
        persons.addToRear(person);
    }
    
    /**
     * Remove a person of the division.
     * 
     * @param person person to remove 
     * @throws PersonNullException if the person in null
     */
    @Override
    public void removePerson(Person person) throws PersonNullException {
        if (person == null) {
            throw new PersonNullException("Person is null!");
        }
        
        persons.remove(person);
    }
    
    /**
     * Adds a new item to the division.
     * 
     * @param item item to add in division
     * @throws ItemNullException if the person in null
     */
    @Override
    public void addItem(Item item) throws ItemNullException {
        if (item == null) {
            throw new ItemNullException("Item is null!");
        }
        
        if (itens.size() < MAX_ITENS) {
            itens.addToRear(item);
        } 
        //deve levar exception??
    }
    
    /**
     * Remove a item of the division.
     * 
     * @param item item to remove 
     * @throws ItemNullException if the person in null
     */
    @Override
    public void removeItem(Item item) throws ItemNullException {
        if (item == null) {
            throw new ItemNullException("Item is null!");
        }
        
        itens.remove(item);
    }
    
    /**
     * String representation of the division.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return "Division name: " + name + "Persons: " + persons.toString() + "Itens: " + itens.toString();
    }
}
