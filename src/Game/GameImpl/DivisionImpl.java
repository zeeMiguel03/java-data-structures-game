/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Exceptions.ItemNullException;
import Game.Exceptions.PersonNullException;
import Game.Interfaces.*;

/**
 * Implementation of the Division interface, representing a division in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class DivisionImpl implements Division {
    private static final int MAX_ITENS = 2;
    
    private String name;
    private UnorderedListADT<Enemy> enemies;
    private UnorderedListADT<Item> itens;
    private boolean entranceExit;
    private Target target;
    private Player player;
    
    /**
     * Constructor for the division class.
     * 
     * @param name the name of the division
     * @param entranceExit true if is a exit or entrance
     */
    public DivisionImpl(String name, boolean entranceExit) {
        this.name = name;
        this.entranceExit = entranceExit;
        this.enemies = new LinkedUnorderedList<>();
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
        this.enemies = new LinkedUnorderedList<>();
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
        return enemies;
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
            throw new PersonNullException("Enemy is null!");
        }
        
        enemies.addToRear(enemy);
    }

    /**
     * Adds the player to the division.
     * 
     * @param player player to add
     */
    @Override
    public void addPlayer(Player player) {
        if (player == null) {
            throw new PersonNullException("Player is null!");
        }

        this.player = player;
    }
    
    /**
     * Remove a enemy of the division.
     * 
     * @param enemy person to remove
     * @throws PersonNullException if the person in null
     */
    @Override
    public void removeEnemy(Enemy enemy) throws PersonNullException {
        if (enemy == null) {
            throw new PersonNullException("Person is null!");
        }
        
        enemies.remove(enemy);
    }

    /**
     * Removes the player of the divison.
     * 
     * @param player the player to remove
     */
    @Override
    public void removePlayer(Player player) {
        if (player == null) {
            throw new PersonNullException("Player is null!");
        }

        this.player = null;
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
     * Returns the target of the division.
     * 
     * @return the division target
     */
    @Override
    public Target getTarget() {
        return target;
    }
    
    /**
     * Returns the player.
     * 
     * @return the player
     */
    @Override
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Sets the target of the division.
     * 
     * @param target the division target
     */
    @Override
    public void setTarget(Target target) {
        this.target = target;
    }

    /**
     * Compares this DivisionImpl name with another object.
     *
     * @param obj obj to compare to
     * @return true if they are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DivisionImpl other = (DivisionImpl) obj;

        return this.name.equals(other.name);
    }

    /**
     * String representation of the division.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return this.name;
    }
}
