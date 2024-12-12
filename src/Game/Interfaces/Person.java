/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

/**
 * @author Miguel Rocha
 */
public interface Person {
    /**
     * Return the name of the person.
     * 
     * @return person name
     */
    public String getName();
    
    /**
     * Sets the name of the person.
     * 
     * @param name name to set
     */
    public void setName(String name);
    
    /**
     * Returns the position of the person.
     * 
     * @return person position
     */
    public Division getDivision();
    
    /**
     * Sets the division of the person.
     * 
     * @param division person division
     */
    public void setDivision(Division division);
    
    /**
     * Return the Power of the person.
     * 
     * @return person power.
     */
    public int getPower();
    
    /**
     * Sets the power of the person.
     * 
     * @param power the person power
     */
    public void setPower(int power);

    /**
     * Returns the life of the person.
     *
     * @return the player life
     */
    public int getLife();

    /**
     * Sets the life of the person.
     *
     * @param life life to set
     */
    public void setLife(int life);

    /**
     * Atacks.
     */
    public abstract void atack();

    /**
     * Change the divison of the person
     *
     * @param division where the person will go
     */
    public void changeDivision(Division division);
}
