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
     * Returns the life of the player.
     * 
     * @return the player life
     */
    public int getLife();
    
    /**
     * Sets the life of the player.
     * 
     * @param life life to set
     */
    public void setLife(int life);
    
    /**
     * Use the last item of the backpack.
     */
    public void useItem();
}
