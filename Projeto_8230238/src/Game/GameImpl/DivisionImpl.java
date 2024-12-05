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
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Target;

/**
 * Implementation of the Division interface, representing a division in the game.
 * 
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class DivisionImpl implements Division {
    private static final int MAX_ITENS = 2;
    
    private String name;
    private UnorderedListADT<Enemy> enemys;
    private UnorderedListADT<Item> itens;
    private boolean entranceExit;
    private Target target; //pensar sobre isto
    
    /**
     * Constructor for the division class.
     * 
     * @param name the name of the division
     * @param entranceExit true if is a exit or entrance
     */
    public DivisionImpl(String name, boolean entranceExit) {
        this.name = name;
        this.entranceExit = entranceExit;
        this.enemys = new LinkedUnorderedList<>();
        this.itens = new LinkedUnorderedList<>();
        this.target = null;
    }
    
    /**
     * Constructor for the division class.
     * 
     * @param name the name of the division
     * @param entranceExit true if is a exit or entrance
     * @param target the target in division
     */
    public DivisionImpl(String name, boolean entranceExit, Target target) {
        this.name = name;
        this.entranceExit = entranceExit;
        this.enemys = new LinkedUnorderedList<>();
        this.itens = new LinkedUnorderedList<>();
        this.target = target;
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
     * Return the Enemys in the division.
     * 
     * @return the Enemys in the division
     */
    @Override
    public UnorderedListADT<Enemy> getEnemysInDivision() {
        return enemys;
    }
    
    /**
     * Sets the enemys in the division.
     * 
     * @param enemys enemys to set
     */
    @Override
    public void setEnemysInDivision(UnorderedListADT<Enemy> enemys) {
        this.enemys = enemys;
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
     * Adds a new enemy to the division.
     * 
     * @param enemy enemy to add in division
     * @throws PersonNullException if the person in null
     */
    @Override
    public void addEnemy(Enemy enemy) throws PersonNullException {
        if (enemy == null) {
            throw new PersonNullException("Person is null!");
        }
        
        enemys.addToRear(enemy);
    }
    
    /**
     * Remove a enemy of the division.
     * 
     * @param enemy enemy to remove 
     * @throws PersonNullException if the person in null
     */
    @Override
    public void removeEnemy(Enemy enemy) throws PersonNullException {
        if (enemy == null) {
            throw new PersonNullException("Person is null!");
        }
        
        enemys.remove(enemy);
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
        //deve levar exception se nao poder levar mais itens??
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
        return "Division name: " + name + "Persons: " + enemys.toString() + "Itens: " + itens.toString();
    }
}
