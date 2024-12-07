/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Exceptions.ElementNotFoundException;
import Game.Exceptions.InvalidEntranceException;
import Game.Interfaces.Building;
import Game.Interfaces.Division;
import Game.Interfaces.Mission;
import Game.Interfaces.Person;
import java.util.Scanner;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class MissionImpl implements Mission {
    private String codMission;
    private int version;
    private Building building;
    private Person player;
    
    /**
     * Constructor for the MissionImpl class.
     * 
     * @param code the code of the mission
     * @param version the version of the mission
     * @param building the building of the mission
     */
    public MissionImpl(String code, int version, Building building) {
        this.codMission = code;
        this.version = version;
        this.building = building;
        this.player = new PlayerImpl();
    }
    
    /**
     * Constructor for the MissionImpl class.
     * 
     * @param code the code of the mission
     * @param version the version of the mission
     */
    public MissionImpl(String code, int version) {
        this.codMission = code;
        this.version = version;
        this.building = new BuildingImpl();
        this.player = new PlayerImpl();
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
    public Person getPlayer() {
        return player;
    }
    
    /**
     * Sets the player of the mission.
     * 
     * @param player the player to set
     */
    @Override
    public void setPlayer(Person player) {
        this.player = player;
    }
        
    @Override
    public void manualSimulation() {
        chooseEntrance();
        
        System.out.println(player.getDivision().getName());
    }
    
    @Override
    public void automaticSimulation() {
    }
    
    /**
     * This method prints the entrance or exit divisions, so that the
     * player can choose it.
     * 
     * @throws InvalidEntranceException if the chosen division was not a entrance
     */
    private void chooseEntrance() throws InvalidEntranceException {
        Scanner scanner = new Scanner(System.in);
        
        building.printEntranceExit();
        System.out.println("Choose a entrance: ");
        
        String entrance = scanner.nextLine();
        
        try {
            Division division = building.searchDivisionByName(entrance);
            
            if (!division.getEntranceExit()) {
                throw new InvalidEntranceException("this division is not an entry");
            }
            
            player.setDivision(division);
        } catch (ElementNotFoundException e) {
            System.out.println(e);
        }
        
        scanner.close();
    }
}
