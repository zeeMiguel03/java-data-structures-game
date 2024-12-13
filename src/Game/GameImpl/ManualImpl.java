package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Enums.typeItem;
import Game.Interfaces.*;
import Game.Json.KeyNotFoundException;
import Game.Reports.Reports;
import GameMenus.MenuImpl;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;

/**
 * This class implements the interface manual.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class ManualImpl extends GameImpl implements Manual{
    private MenuImpl menu;
    private boolean isEndTrue;
    private boolean continuarNoEdificio;
    private boolean pegouKit;
    private UnorderedListADT<String> pathDivisions;

    /**
     * Constructor for Manual Mission class.
     */
    public ManualImpl() throws IOException, ParseException, KeyNotFoundException {
        super();
        super.loadGame();
        menu = new MenuImpl();
        isEndTrue = false;
        continuarNoEdificio = false;
        pegouKit = false;
        pathDivisions = new LinkedUnorderedList<>();
    }

    /**
     * Starts the game manually.
     */
    @Override
    public void startGameManual()  {
        Player player = getPlayer();
        pathDivisions.addToRear(player.getDivision().getName());

        while (!isEndTrue) {
            informationsAboutDivision();

            if (!player.getDivision().getEnemysInDivision().isEmpty()) {
                menu.menuDuringFase(this);

                if (!player.getDivision().getEnemysInDivision().isEmpty() && !pegouKit && !continuarNoEdificio) {
                    updateEnemy();
                    getBuilding().updateConnections(player);
                    enemyAttack(player.getDivision());
                }

            } else if (player.getDivision().getEnemysInDivision().isEmpty() && !player.getDivision().getItemsInDivision().isEmpty() || player.getLife() < player.getMaxLife() && !player.getBackpack().isEmpty() || player.getDivision().isEntranceExit() && !continuarNoEdificio || player.getDivision().getTarget() != null) {
                updateEnemy();
                menu.menuDuringFase(this);

                if (!pegouKit && !continuarNoEdificio) {
                    updateEnemy();
                    getBuilding().updateConnections(player);
                }

            } else {
                pegouKit = false;
                continuarNoEdificio = false;
                updateEnemy();
                getBuilding().updateConnections(player);

                if (!player.getDivision().getEnemysInDivision().isEmpty() && !pegouKit && !continuarNoEdificio) {
                    updateEnemy();
                    getBuilding().updateConnections(player);
                    enemyAttack(player.getDivision());
                    continue;
                }

                menu.menuChangeDivision(player.getDivision(), this);
                pathDivisions.addToRear(player.getDivision().getName());
            }

            if (player.getLife() <= 0) {
                setIsEndTrue();
            }

        }

        if (!player.getHaveTarget() || !player.getDivision().isEntranceExit()) {
            player.setLife(0);
            System.out.println("Mission Failed");
        } else {
            System.out.println("Mission Passed");
        }
        Reports.generateReport(this);
    }

    /**
     * Sets the game end to true.
     */
    @Override
    public void setIsEndTrue() {
        isEndTrue = true;
    }

    /**
     * Sets true that the player will remain in the building.
     */
    @Override
    public void setContinueInBuilding() {
        continuarNoEdificio = true;
    }

    /**
     * Sets true that the player has picked up a kit.
     */
    @Override
    public void setPickedKit() {
        pegouKit = true;
    }

    /**
    * Executes enemy attacks in the specified division.
    * @param division the division where the player is.
    */
    private void enemyAttack(Division division) {
        for (Enemy enemy : division.getEnemysInDivision()) {
            enemy.atack();
        }
    }

    /**
     * Displays information about the current division and about the rest, including paths to the target and kits.
     */
    private void informationsAboutDivision (){
        informationGame();
        Iterator shortestPath = null;

        if (getTarget().getDivision() != null) {
            shortestPath = getBuilding().getDivisions().iteratorShortestPath(getPlayer().getDivision(), getTarget().getDivision());
        }
        Iterator shortestPathToKit = null;
        Item itemMaisProximo = null;
        int distancia = 0;

        for (Item item : getItems()) {
            if (item.getDivision() == null) {
                continue;
            }

            if (item.getType() == typeItem.KIT_LIFE){
                if (itemMaisProximo == null) {
                    itemMaisProximo = item;
                    distancia = getBuilding().getDivisions().getDistance(getPlayer().getDivision(), item.getDivision());
                } else {
                    int distanciaTmp = getBuilding().getDivisions().getDistance(getPlayer().getDivision(), item.getDivision());

                    if (distanciaTmp < distancia) {
                        distancia = distanciaTmp;
                        itemMaisProximo = item;
                    }
                }
            }
        }
        if (itemMaisProximo != null) {
            shortestPathToKit = getBuilding().getDivisions().iteratorShortestPath(getPlayer().getDivision(), itemMaisProximo.getDivision());
        }

        System.out.println("\n\n----------------Shortest Path To Target---------------------");
        if (!getPlayer().getHaveTarget()) {
            while (shortestPath.hasNext()) {
                Division div = (Division) shortestPath.next();
                System.out.print(div.getName());

                if (shortestPath.hasNext()) {
                    System.out.print(" ---> ");
                }
            }
        } else {
            System.out.print("Already Have Target!");
        }

        System.out.println("\n\n----------------Shortest Path To Kit---------------------");
        System.out.print("Shortest Path to kit: ");
        
        if (shortestPathToKit == null) {
            System.out.print("No path to kit!");
        } else {
            while (shortestPathToKit.hasNext()) {
                Division div = (Division) shortestPathToKit.next();
                System.out.print(div.getName());

                if (shortestPathToKit.hasNext()) {
                    System.out.print(" ---> ");
                }
            }
        }

        System.out.println("\n--------------------------------------------------\n\n");

        if (!getPlayer().getDivision().getEnemysInDivision().isEmpty()) {
            for (Enemy enemy : getPlayer().getDivision().getEnemysInDivision()) {
                System.out.println("Enemy: " + enemy.getName() + " Life: " + enemy.getLife());
            }
        }

        if (!getPlayer().getDivision().getItemsInDivision().isEmpty()) {
            for (Item item : getPlayer().getDivision().getItemsInDivision()) {
                System.out.println("Item: " + item.getType() + " Pontos: " + item.getPoints());
            }
        }

        if (getPlayer().getDivision().getTarget() != null) {
            System.out.println("Tu estás na divisão do alvo ---> Alvo: " + getPlayer().getDivision().getTarget().getType());
        }
        
        System.out.println("Player: " + getPlayer().getName() + " Life: " + getPlayer().getLife() + " Division: " + getPlayer().getDivision().getName());
    }

    /**
     * returns the divisions traversed during the game.
     *
     * @return an unordered list containing the names of the divisions
     */
    @Override
    public UnorderedListADT<String> getPathDivisions() {
        return pathDivisions;
    }
}
