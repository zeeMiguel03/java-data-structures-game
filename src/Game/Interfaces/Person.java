/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

/**
 *  A interface representing of the Person in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Person {
    /**
     * Return the name of the person.
     * 
     * @return person name
     */
    String getName();
    
    /**
     * Sets the name of the person.
     * 
     * @param name name to set
     */
    void setName(String name);
    
    /**
     * Returns the position of the person.
     * 
     * @return person position
     */
    Division getDivision();
    
    /**
     * Sets the division of the person.
     * 
     * @param division person division
     */
    void setDivision(Division division);
    
    /**
     * Return the Power of the person.
     * 
     * @return person power.
     */
    int getPower();
    
    /**
     * Sets the power of the person.
     * 
     * @param power the person power
     */
    void setPower(int power);

    /**
     * Returns the life of the person.
     *
     * @return the player life
     */
    int getLife();

    /**
     * Sets the life of the person.
     *
     * @param life life to set
     */
    void setLife(int life);

    /**
     * Atacks.
     */
    void atack();
}
