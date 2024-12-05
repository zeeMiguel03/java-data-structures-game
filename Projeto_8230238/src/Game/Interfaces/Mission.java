/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

/**
 *
 * @author Miguel
 */
public interface Mission {
    
    /**
     * Returns the code of the mission.
     * 
     * @return the mission code
     */
    public String getCodMission();
    
    /**
     * Sets the mission code.
     * 
     * @param mission mission code
     */
    public void setCodMission(String mission);
    
    /**
     * Returns the version of the mission.
     * 
     * @return the version of the mission
     */
    public int getVersion();
    
    /**
     * Sets the version of the mission.
     * 
     * @param version mission version
     */
    public void setVersion(int version);
    
    /**
     * Returns the building of the mission.
     * 
     * @return the building
     */
    public Building getBuilding();
    
    /**
     * Sets the building of the mission.
     * 
     * @param building the building of the mission
     */
    public void setBuilding(Building building);
    
    /**
     * Returns the player of the mission.
     * 
     * @return the player of the mission
     */
    public Player getPlayer();
    
    /**
     * Sets the player of the mission.
     * 
     * @param player the player to set
     */
    public void setPlayer(Player player);
}
