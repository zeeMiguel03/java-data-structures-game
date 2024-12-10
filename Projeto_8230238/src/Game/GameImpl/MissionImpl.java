/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Exceptions.ElementNotFoundException;
import Game.Exceptions.EndOfMissionException;
import Game.Exceptions.InvalidEntranceException;
import Game.Interfaces.*;

import java.util.Scanner;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class MissionImpl implements Mission {
    private String codMission;
    private int version;
    private Building building;
    private Player player;
    private boolean missionStarted;
    
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
        this.missionStarted = false;
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
    public void setPlayer(Player player) {
        this.player = player;
    }
        
    @Override
    public void manualSimulation() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            chooseEntrance(scanner);

            while (!verifyEndMission()) {
                missionStarted = true;
                chooseNextPosition(player.getDivision(), scanner);
                 
                if (!player.getDivision().getEnemysInDivision().isEmpty()) {
                    while (player.getLife() > 0 && !player.getDivision().getEnemysInDivision().isEmpty()) {
                        player.atack();
                                
                        for (Enemy enemy : player.getDivision().getEnemysInDivision()) {
                            enemy.atack();
                        }
                        
                        System.out.println(player.getLife());
                        System.out.println(player.getDivision().getEnemysInDivision().size());
                        
                        if (player.getDivision().getEnemysInDivision().isEmpty()) {
                            System.out.println("Ta sem inimigos agora!");
                        }
                    }
                }
    
                if (player.getDivision().getTarget() != null) {
                    System.out.println("You are in the target division!!!");
                    
                    PlayerImpl playerImpl = (PlayerImpl) player;
                    
                    playerImpl.setHaveTarget(true);
                }
                
            }
        } catch (EndOfMissionException e) {
            System.out.println(e.getMessage());
        }
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
    private void chooseEntrance(Scanner scanner) throws InvalidEntranceException {  
        boolean validEntrance = false;

        while (!validEntrance) {
            building.printEntranceExit();
            System.out.println("Choose an entrance: ");

            String entrance = scanner.nextLine();

            try {
                Division division = building.searchDivisionByName(entrance);

                if (division.getEntranceExit()) {
                    player.setDivision(division);
                    division.addPlayer(player);
                    validEntrance = true;
                } else {
                    System.out.println("Invalid entrance. This division is not an entry. Try again.");
                }
            } catch (ElementNotFoundException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
    }

    
    /**
     * This method prints the possible divisions options for the user to choose.
     * 
     * @param currentDivision the current division
     * @param scanner the scanner to read the option
     */
    private void chooseNextPosition(Division currentDivision, Scanner scanner) {
        Division newDivision = null;
        boolean validDivision = false;

        while (!validDivision) {
            building.printNextDivisions(currentDivision);
            System.out.println("Choose the next position: ");

            String nextDivisionName = scanner.nextLine();

            try {
                newDivision = building.searchDivisionByName(nextDivisionName);
                if (building.getDivisions().verifyConnection(currentDivision, newDivision)) {
                    validDivision = true;
                } else {
                    System.out.println("Invalid move. There is no connection between the current division and the selected division. Try again.");
                }
            } catch (ElementNotFoundException e) {
                System.out.println(e);
            }
        }

        player.setDivision(newDivision);
        newDivision.addPlayer(player);
    }
    
    /**
     * Verifies whether the mission has ended.
     * 
     * @return false if the mission was not finish
     * @throws EndOfMissionException  if the mission ends due to player death or mission completion
     */
    private boolean verifyEndMission() throws EndOfMissionException {  
        if (!missionStarted) {
            return false;  
        }
        
        if (player.getLife() <= 0) {
            throw new EndOfMissionException("Tó Cruz died, , end of the Game!!");
        } 
        
        if (player.getDivision().isEntranceExit()) {
            PlayerImpl playerImpl = (PlayerImpl) player;
            
            if (playerImpl.getHaveTarget() && player.getDivision().getEnemysInDivision().isEmpty()) {
                throw new EndOfMissionException("Tó Cruz Win, end of the Game!!");
            } else if (!playerImpl.getHaveTarget()) {
                throw new EndOfMissionException("To Cruz forgot the target, end of the Game!!");
            }
            
        }
                
        return false;
    }
}
