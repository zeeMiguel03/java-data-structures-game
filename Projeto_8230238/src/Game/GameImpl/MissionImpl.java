/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Game.Interfaces.Building;
import Game.Interfaces.Mission;
import Game.Interfaces.Player;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class MissionImpl implements Mission {
    private String codMission;
    private int version;
    private Building building;
    private Player player;
    
    /**
     * Constructor for the MissionImpl class.
     * 
     * @param code the code of the mission
     * @param version the version of the mission
     * @param building the building of the mission
     * @param player the mission player
     */
    public MissionImpl(String code, int version, Building building, Player player) {
        this.codMission = code;
        this.version = version;
        this.building = building;
        this.player = player;
    }
    
    /**
     * Returns the code of the mission.
     * 
     * @return the mission code
     */
    @Override
    public String getCodMission() {
        return codMission;
    }
    
    /**
     * Sets the mission code.
     * 
     * @param mission mission code
     */
    @Override
    public void setCodMission(String mission) {
        this.codMission = mission;
    }
    
    /**
     * Returns the version of the mission.
     * 
     * @return the version of the mission
     */
    @Override
    public int getVersion() {
        return version;
    }
    
    /**
     * Sets the version of the mission.
     * 
     * @param version mission version
     */
    @Override
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * Returns the building of the mission.
     * 
     * @return the building
     */
    @Override
    public Building getBuilding() {
        return building;
    }
    
    /**
     * Sets the building of the mission.
     * 
     * @param building the building of the mission
     */
    @Override
    public void setBuilding(Building building) {
        this.building = building;
    }
    
    /**
     * Returns the mission player.
     * 
     * @return the player
     */
    @Override
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Sets the player of the mission.
     * 
     * @param player the player to set
     */
    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
        
    @Override
    public void manualSimulation() {
        
    }
    
    @Override
    public void automaticSimulation() {
        
    }
}
