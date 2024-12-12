/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Queues.QueueADT;
import Collections.Stacks.LinkedStack;
import Collections.Stacks.StackADT;
import Game.Enums.typeItem;
import Game.Exceptions.EndOfMissionException;
import Game.Interfaces.Division;
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Player;
import Game.Json.KeyNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class AutomaticImpl extends Game{
    private Division bestEntrance;
    private Division bestExit;
    private String result;
    Enemy enemyEncontrado;
    Division divisionExit;
    
    public AutomaticImpl() throws IOException, ParseException, KeyNotFoundException {
        super();
        super.loadGame();
        this.result = " ";
        enemyEncontrado = null;
        divisionExit = null;
    }
    
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
                bestPathPrint(division);
                return;
            } else if (!iteratorEntrance.hasNext()){
                System.out.println("\n" + "Tó Cruz, successfully reached the target!");
                getPlayer().setHaveTarget();
            }

            bestPathPrint(division);
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

    private void enemyTurn() {
        for (Enemy enemy : getPlayer().getDivision().getEnemysInDivision()) {

            if (enemyEncontrado != null && !enemyEncontrado.equals(enemy)) {
                enemyEncontrado = enemy;
                System.out.println("Player encontrou " + enemy.getName() + "!");
            } else if (enemyEncontrado == null) {
                enemyEncontrado = enemy;
                System.out.println("Player encontrou " + enemy.getName() + "!");
            }

            enemy.atack();

            if (enemy.getLife() <= 0) {
                System.out.println(getPlayer().getName() + " killed " + enemy.getName() + " in the position: " + enemy.getDivision().getName());
            }

        }

        updateEnemy();
        getBuilding().updateConnections(getPlayer());
    }
        
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

    private void bestPathPrint(Division division) {
        result += " --> " + division.getName();


        /*if (division.getTarget() != null || getPlayer().getLife() <= 0) {
            System.out.println("The best path was: " + result);
        }*/
    }

    private void reversePathPrint(Division division) {
        result += " --> " + division.getName();


        if (getPlayer().getHaveTarget() && getPlayer().getDivision().equals(divisionExit) || getPlayer().getLife() <= 0) {
            System.out.println("The best path was: " + result + "\nTó Cruz life was: " + Math.max(getPlayer().getLife(), 0));
        }
    }
}
