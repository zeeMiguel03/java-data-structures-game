/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Collections.Lists.UnorderedListADT;
import Game.Exceptions.ItemNullException;
import Game.Exceptions.PersonNullException;

/**
 * Interface defining the methods for a division in the game.
 * 
 * @author Miguel Rocha
 * @author António Monteiro
 */
public interface Division {

    /**
     * Returns the name of the division.
     * 
     * @return division name
     */
    public String getName();
    
    /**
     * Sets the division name.
     * 
     * @param name name to set
     */
    public void setName(String name);
    
    /**
     * Returns if the division is a entrance or a exit.
     * 
     * @return if the division is a entrance or a exit
     */
    public boolean getEntranceExit();
    
    /**
     * Sets if the division is a entrance or a exit.
     * 
     * @param entranceExit option to set
     */
    public void setEntranceExit(boolean entranceExit);
    
    /**
     * Return the enemys in the division.
     * 
     * @return the enemys in the division
     */
    public UnorderedListADT<Enemy> getEnemysInDivision();
    
    /**
     * Sets the enemys in the division.
     * 
     * @param enemys enemys to set
     */
    public void setEnemysInDivision(UnorderedListADT<Enemy> enemys);

    /**
     * Returns the item in the division.
     * 
     * @return the items in division
     */
    public UnorderedListADT<Item> getItemsInDivision();
    
    /**
     * Sets the itens in the division.
     * 
     * @param itens itens to set
     */
    public void setItensInDivision(UnorderedListADT<Item> itens);

    /**
     * Return true if the division is a division that you can use as input or output
     * 
     * @return true if the division is a division that you can use as input or output
     */
    public boolean isEntranceExit();
    
    /**
     * Adds a new enemy to the division.
     * 
     * @param enemy enemy to add in division
     * @throws PersonNullException if the enemy in null
     */
    public void addEnemy(Enemy enemy) throws PersonNullException;
    
    /**
     * Remove a enemy of the division.
     * 
     * @param enemy enemy to remove 
     * @throws PersonNullException if the person in null
     */
    public void removeEnemy(Enemy enemy) throws PersonNullException;
    
    /**
     * Adds a new item to the division.
     * 
     * @param item item to add in division
     * @throws ItemNullException if the person in null
     */
    public void addItem(Item item) throws ItemNullException;
    
    /**
     * Remove a item of the division.
     * 
     * @param item item to remove 
     * @throws ItemNullException if the person in null
     */
    public void removeItem(Item item) throws ItemNullException;
    
    /**
     * Returns the target of the division.
     * 
     * @return the division target
     */
    public Target getTarget();
    
    /**
     * Sets the target of the division.
     * 
     * @param target the division target
     */
    public void setTarget(Target target);
}
