/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Collections.Stacks.StackADT;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public interface Player extends Person{

    /**
     * Sets the player have target true
     */
    void setHaveTarget();

    /**
     * Returns the player backpack.
     *
     * @return the backpack
     */
    StackADT<Item> getBackpack();

    /**
     * Sets the player backpack.
     *
     * @param itens itens to set
     */
    void setBackpack(StackADT<Item> itens);

    /**
     * Return if the player have the target
     *
     * @return return if the player have the target
     */
    boolean getHaveTarget();

    /**
     * Use the last item of the backpack.
     */
    void useMedicKit();

    /**
     * Pick the item and save it or use it.
     */
    void pickItem();

    /**
     * Player use the vest if the division have one.
     */
    void useVest();

    /**
     * Returns the player max life
     * 
     * @return player max life
     */
    int getMaxLife();

}