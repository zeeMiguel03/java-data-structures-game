/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Stacks.LinkedStack;
import Collections.Stacks.StackADT;
import Game.Enums.typeItem;
import Game.Interfaces.Division;
import Game.Interfaces.Player;

/**
 * @author Miguel Rocha
 */
public class PlayerImpl extends PersonImpl implements Player {
    private static final int MAX_LIFE = 100;
    private static final int POWER_PLAYER = 20;
    
    private int lifePoints;
    private StackADT<ItemImpl> backpack;
    
    /**
     * Constructor for Player class.
     * 
     * @param division the player division
     */
    public PlayerImpl(Division division, int power) {
        super("Tó Cruz", division, POWER_PLAYER);
        
        this.lifePoints = MAX_LIFE;
        this.backpack = new LinkedStack<>();
    }

    /**
     * Returns the life of the player.
     * 
     * @return the player life
     */
    @Override
    public int getLife() {
        return lifePoints;
    }

    /**
     * Sets the life of the player.
     * 
     * @param life life to set
     */
    @Override
    public void setLife(int life) {
        this.lifePoints = life;
    }

    /**
     * Use the last item of the backpack.
     */
    @Override
    public void useItem() {
        //Falta meter máximo
        
        ItemImpl item = backpack.pop();
            
        if (item.getType().equals(typeItem.KIT_LIFE)) {
            if (lifePoints + item.getPoints() > MAX_LIFE) {
                    lifePoints = MAX_LIFE;
                    return;
            }
        }
            
        lifePoints += item.getPoints();
    }    
}
