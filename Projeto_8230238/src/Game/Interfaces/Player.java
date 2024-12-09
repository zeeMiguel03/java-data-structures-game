/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Collections.Stacks.StackADT;

/**
 * @author Miguel Rocha
 */
public interface Player {

    /**
     * Returns the player backpack.
     *
     * @return the backpack
     */
    public StackADT<Item> getBackpack();

    /**
     * Sets the player backpack.
     *
     * @param itens itens to set
     */
    public void setBackpack(StackADT<Item> itens);

    /**
     * Return if the player have the target
     *
     * @return return if the player have the target
     */
    public boolean getHaveTarget();

    /**
     * Sets if the player have the target or not
     *
     * @param haveTarget true or false
     */
    public void setHaveTarget(boolean haveTarget);

    /**
     * Use the last item of the backpack.
     */
    public void useMedicKit();

    /**
     * Pick the item and save it or use it.
     */
    public void pickItem();
}