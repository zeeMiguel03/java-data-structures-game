/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Queues.QueueADT;
import Collections.Stacks.StackADT;
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
public class AutomaticImpl {
    private Division bestEntrance;
    private String result;
    Enemy enemyEncontrado;
    
    public AutomaticImpl() {
        super();
        this.result = " ";
        enemyEncontrado = null;
    }
    
    public void startGameAutomatic(Game game) {
        Player player = game.getPlayer();
        Division division1 = getBestEntrance(game);
        Division division2 = game.getTarget().getDivision();
        
        Iterator<Division> iterator = game.getBuilding().getDivisions().iteratorShortestPath(division1, division2);
        player.setDivision(division1);
        
        while (iterator.hasNext()) {
            Division division = iterator.next();
            playerTurn(game);
            
            if (player.getLife() <= 0) {
                System.out.println("Tó Cruz died int the position: " + player.getDivision().getName());
                bestPathPrint(division, game);
                return;
            } else if (!iterator.hasNext()){
                System.out.println("\n" + "Tó Cruz, successfully reached the target!");
            }

            bestPathPrint(division, game);
            player.setDivision(division);
            division.addPlayer(player);
        }

    }
    
    private void playerTurn(Game game) {
        Player player = game.getPlayer();

        if (!player.getDivision().getEnemysInDivision().isEmpty()){
            
            while (player.getLife() > 0 && !player.getDivision().getEnemysInDivision().isEmpty()) {
                
                if (player.getLife() < 70 && !player.getBackpack().isEmpty()) {
                    player.useMedicKit();
                } else {
                    player.atack();
                }

                enemyTurn(game, player);
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

    private void enemyTurn(Game game, Player player) {
        for (Enemy enemy : player.getDivision().getEnemysInDivision()) {

            if (enemyEncontrado != null && !enemyEncontrado.equals(enemy)) {
                enemyEncontrado = enemy;
                System.out.println("Player encontrou " + enemy.getName() + "!");
            } else if (enemyEncontrado == null) {
                enemyEncontrado = enemy;
                System.out.println("Player encontrou " + enemy.getName() + "!");
            }

            enemy.atack();

            if (enemy.getLife() <= 0) {
                System.out.println(player.getName() + " killed " + enemy.getName() + " in the position: " + enemy.getDivision().getName());
            }

        }

        game.updateEnemy();
        game.getBuilding().updateConnections(player);
    }
        
    private Division getBestEntrance(Game game) {
        QueueADT<Division> entrance = game.getBuilding().getEntranceExit();
        
        double lessDamage = 1000;
        int size = entrance.size();

        for (int i = 0; i < size; i++) {
            Division currentDivision = entrance.dequeue();
            
            double pathLife = game.getBuilding().getDivisions().shortestPathWeight(currentDivision, game.getTarget().getDivision());

            if (pathLife < lessDamage) {
                lessDamage = pathLife;
                bestEntrance = currentDivision;
            }
        }

        return bestEntrance;
    }

    private void bestPathPrint(Division division, Game game) {
        result += " --> " + division.getName();
        //reversePathPrint(division, game);

        if (division.getTarget() != null || game.getPlayer().getLife() <= 0) {
            System.out.println("The best path was: " + result + "\nTó Cruz life was: " + Math.max(game.getPlayer().getLife(), 0));
        }
    }

    /*private void reversePathPrint(Division division, Game game) {
        StackADT<String> divisionsPathReverse = mew linkedStack<>();

        divisionsPathReverse.push(division.getName());

        if (division.getTarget() != null || game.getPlayer().getLife() <= 0) {
            while (!divisionsPathReverse.isEmpty()) {
                result += " --> " + divisionsPathReverse.pop();
            }
        }
    }*/
}
