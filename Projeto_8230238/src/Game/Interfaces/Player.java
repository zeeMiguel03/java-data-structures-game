/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

/**
 * @author Miguel Rocha
 */
public interface Player {
    
    /**
     * Use the last item of the backpack.
     */
    public void useItem();

    /**
     * Pick the item and save it
     *
     * @param item item to pick
     */
    public void pickItem(Item item);
}
