/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

/**
 *
 * @author Miguel
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
}
