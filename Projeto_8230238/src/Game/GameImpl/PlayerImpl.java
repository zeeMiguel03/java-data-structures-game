/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Stacks.LinkedStack;
import Collections.Stacks.StackADT;
import Game.Enums.typeItem;
import Game.Interfaces.*;

/**
 * @author Miguel Rocha
 */
public class PlayerImpl extends PersonImpl implements Player {
    private static final int MAX_LIFE = 100;
    private static final int MAX_ITEMS = 3;
    private static final int POWER_PLAYER = 20;
    private static final String PLAYER_NAME = "Tó Cruz";

    private StackADT<Item> backpack;

    /**
     * Constructor for Player class.
     *
     * @param division the player division
     */
    public PlayerImpl(Division division) {
        super(PLAYER_NAME, division, POWER_PLAYER, MAX_LIFE);
        
        backpack = new LinkedStack<>();
    }
    
    /**
     * Returns the player backpack.
     * 
     * @return the backpack
     */
    @Override
    public StackADT<Item> getBackpack() {
        return backpack;
    }
    
    /**
     * Sets the player backpack.
     * 
     * @param itens itens to set
     */
    @Override
    public void setBackpack(StackADT<Item> itens) {
        this.backpack = itens;
    }

    @Override
    public void atack() {
        for (Enemy enemy : getDivision().getEnemysInDivision()) {
            enemy.setLife(enemy.getLife() - getPower());
        }
    }

    /**
     * Use the last medic kit of the backpack.
     */
    @Override
    public void useMedicKit() {
        Item item = backpack.pop();
            
        if (getLife() + item.getPoints() > MAX_LIFE) {
            setLife(MAX_LIFE);
            return;
        }
        
        setLife(getLife() + item.getPoints());
    }

    /**
     * Pick the item and save it or use it.
     */
    @Override
    public void pickItem() {
        for (Item item : getDivision().getItemsInDivision()) {
            if (item.getType().equals(typeItem.KIT_LIFE)) {
                if (backpack.size() < MAX_ITEMS) {
                    backpack.push(item);
                }
            } else {
                setLife(getLife() + item.getPoints());
            }
        }
    }
}
