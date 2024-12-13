/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Queues.QueueADT;
import Game.Enums.typeItem;
import Game.Interfaces.*;
import Game.Json.KeyNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;

/**
 *  A class representing a automatic mode in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class AutomaticImpl extends Game implements Automatic {
    private Division bestEntrance;
    private Division bestExit;
    private String result;
    Enemy enemyFound;
    Division divisionExit;

    /**
     * Constructor for the AutomaticImpl class.
     *
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    public AutomaticImpl() throws IOException, ParseException, KeyNotFoundException {
        super();
        super.loadGame();
        this.result = " ";
        enemyFound = null;
        divisionExit = null;
    }

    /**
     * Starts the game automatically, where the player begins in the best entrance and navigates
     * through the building until reach the target and doing is turns, and once he gets the target,
     * he proceeds to the exit.
     */
    @Override
    public void startGameAutomatic() {
        Player player = getPlayer();
        Division divisionEntrance = getBestEntrance();
        divisionExit = getBestExit();
        Division division2 = getTarget().getDivision();
        
        Iterator<Division> iteratorEntrance = getBuilding().getDivisions().iteratorShortestPath(divisionEntrance, division2);
        player.setDivision(divisionEntrance);
        
        while (iteratorEntrance.hasNext()) {
            Division division = iteratorEntrance.next();
            playerTurn();
            
            if (player.getLife() <= 0) {
                System.out.println("Tó Cruz died int the position: " + player.getDivision().getName());
                bestPathToTarget(division);
                return;
            } else if (!iteratorEntrance.hasNext()){
                System.out.println("\n" + "Tó Cruz, successfully reached the target!");
                getPlayer().setHaveTarget();
            }

            bestPathToTarget(division);
            player.setDivision(division);
            division.addPlayer(player);
        }
        result += "/ He found the target now the way out/";
        Iterator<Division> iteratorExit = getBuilding().getDivisions().iteratorShortestPath(division2, divisionExit);
        player.setDivision(divisionEntrance);

        while (iteratorExit.hasNext()) {
            Division division = iteratorExit.next();
            playerTurn();

            if (player.getLife() <= 0) {
                System.out.println("Tó Cruz died in the position: " + player.getDivision().getName());
                reversePathPrint(division);
                return;
            } else if (!iteratorExit.hasNext()){
                System.out.println("\n" + "Tó Cruz, successfully exit the building with target!");
            }

            divisionExit = getBestExit();
            player.setDivision(division);
            reversePathPrint(division);
            division.addPlayer(player);
        }
    }

    /**
     * Represents the player turn, where  there are several scenarios.
     */
    private void playerTurn() {
        Player player = getPlayer();

        if (!player.getDivision().getEnemysInDivision().isEmpty()){
            
            while (player.getLife() > 0 && !player.getDivision().getEnemysInDivision().isEmpty()) {
                
                if (player.getLife() < 70 && !player.getBackpack().isEmpty()) {
                    player.useMedicKit();
                } else {
                    player.atack();
                }

                enemyTurn();
            }
        }

        if (player.getDivision().getItemsInDivision() != null) {
            for (Item item : player.getDivision().getItemsInDivision()) {
                if (item.getType().equals(typeItem.KIT_LIFE)) {
                    player.pickItem();
                } else {
                    player.useVest();
                }
            }
        }
    }

    /**
     * Represents the enemy's turn, where he can attack the player if he is on it
     * division, or otherwise change the division.
     */
    private void enemyTurn() {
        for (Enemy enemy : getPlayer().getDivision().getEnemysInDivision()) {

            if (enemyFound != null && !enemyFound.equals(enemy)) {
                enemyFound = enemy;
                System.out.println("Player found " + enemy.getName() + "!");
            } else if (enemyFound == null) {
                enemyFound = enemy;
                System.out.println("Player found " + enemy.getName() + "!");
            }

            enemy.atack();

            if (enemy.getLife() <= 0) {
                System.out.println(getPlayer().getName() + " killed " + enemy.getName() + " in the position: " + enemy.getDivision().getName());
            }

        }

        updateEnemy();
        getBuilding().updateConnections(getPlayer());
    }

    /**
     * Returns the best entrance possible in the building.
     *
     * @return the best entrance division
     */
    private Division getBestEntrance() {
        QueueADT<Division> entrance = getBuilding().getEntranceExit();
        
        double lessDamage = 1000;
        int size = entrance.size();

        for (int i = 0; i < size; i++) {
            Division currentDivision = entrance.dequeue();
            
            double pathLife = getBuilding().getDivisions().shortestPathWeight(currentDivision, getTarget().getDivision());

            if (pathLife < lessDamage) {
                lessDamage = pathLife;
                bestEntrance = currentDivision;
            }
        }

        return bestEntrance;
    }

    /**
     * Returns the best exit possible in the building.
     *
     * @return the best exit division
     */
    private Division getBestExit() {
        QueueADT<Division> entrance = getBuilding().getEntranceExit();

        double lessDamage = 1000;
        int size = entrance.size();

        for (int i = 0; i < size; i++) {
            Division currentDivision = entrance.dequeue();

            double pathLife = getBuilding().getDivisions().shortestPathWeight(getTarget().getDivision(), currentDivision);

            if (pathLife < lessDamage) {
                lessDamage = pathLife;
                bestExit = currentDivision;
            }
        }

        return bestExit;
    }

    /**
     * Returns the best Path to the target.
     *
     * @param division the actual Division
     */
    private void bestPathToTarget(Division division) {
        result += " --> " + division.getName();
    }

    /**
     * Returns the best reverse Path until the exit.
     *
     * @param division the actual division
     */
    private void reversePathPrint(Division division) {
        result += " --> " + division.getName();

        if (getPlayer().getHaveTarget() && getPlayer().getDivision().equals(divisionExit) || getPlayer().getLife() <= 0) {
            System.out.println("The best path was: " + result + "\nTó Cruz life was: " + Math.max(getPlayer().getLife(), 0));
        }
    }
}
