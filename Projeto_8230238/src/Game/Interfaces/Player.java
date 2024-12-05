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
    public void getBackpack(StackADT<Item> itens);
    
    /**
     * Use the last item of the backpack.
     */
    public void useMedicKit();

    /**
     * Pick the item and save it or use it.
     */
    public void pickItem();
}
