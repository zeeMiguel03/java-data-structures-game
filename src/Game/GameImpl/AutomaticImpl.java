/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Queues.QueueADT;
import Game.Enums.typeItem;
import Game.Exceptions.EndOfMissionException;
import Game.Interfaces.Division;
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Player;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class AutomaticImpl extends Game{
    private Division bestEntrance;
    
    public AutomaticImpl() {
        super();
    }
    
    public void startGameAutomatic(Game game) {
        Player player = getPlayer();
        Division division1 = getBestEntrance();
        Division division2 = game.getTarget().getDivision();
        
        Iterator<Division> iterator = getBuilding().getDivisions().iteratorShortestPath(division1, division2);
        player.setDivision(division1);
        
        while (iterator.hasNext()) {
            Division division = iterator.next();
            bestPathPrint(division);
            playerTurn();
            
            if (player.getLife() <= 0) {
                throw new EndOfMissionException("T Cruz died!");
            } else if (!iterator.hasNext()){
                System.out.println("\n" + "Tó Cruz, successfully reached the target!");
            }
            
            player.setDivision(division);
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

                for (Enemy enemy : player.getDivision().getEnemysInDivision()) {
                    enemy.atack();
                }

                updateEnemy();
                getBuilding().updateConnections(player);
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

        if (player.getDivision().getTarget() != null) {
            player.setHaveTarget();
        }
    }
        
    public Division getBestEntrance() {
        QueueADT<Division> entrance = getBuilding().getEntranceExit();
        
        double lessDamage = 1000;
        int size = entrance.size();
        
        for (int i = 0; i < size; i++) {
            Division currentDivision = entrance.dequeue();
            
            double pathLife = getBuilding().getDivisions().shortestPathWeight(currentDivision, getBuilding().getItemDivision());
            
            if (pathLife < lessDamage) {
                lessDamage = pathLife;
                bestEntrance = currentDivision;
            }              
        }
                
        return bestEntrance;
    }

    public void bestPathPrint(Division division) {
        String result = " ";

        if (division.getTarget() != null) {
            System.out.println("The best path was: " + result + " and Tó Cruz life was: " + getPlayer().getLife());
        } else {
            result += " " + division.toString();
        }
    }
}
