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
    private static final int POWER_PLAYER = 20;
    private static final int MAX_ITEMS = 3;

    private StackADT<Item> backpack;

    /**
     * Constructor for Player class.
     *
     * @param division the player division
     */
    public PlayerImpl(Division division) {
        super("Tó Cruz", division, POWER_PLAYER, MAX_LIFE);
        backpack = new LinkedStack<>();
    }

    @Override
    public void atack() {
        for (Person person : getDivision().getPersonsInDivision()) {
            if (person instanceof Enemy) {
                person.setLife(person.getLife() - getPower());
            }
        }
    }

    /**
     * Use the last item of the backpack.
     */
    @Override
    public void useItem() {
        Item item = backpack.pop();
            
        if (item.getType().equals(typeItem.KIT_LIFE)) {
            if (getLife() + item.getPoints() > MAX_LIFE) {
                    setLife(MAX_LIFE);
                    return;
            }
        }

        setLife(getLife() + item.getPoints());
    }

    @Override
    public void pickItem(Item item) {
        for (Item items : getDivision().getItemsInDivision()) {
            if (backpack.size() < MAX_ITEMS) {
                backpack.push(items);
            }
        }
    }
}
