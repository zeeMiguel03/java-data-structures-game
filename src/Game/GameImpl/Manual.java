package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Collections.Queues.LinkedQueue;
import Collections.Queues.QueueADT;
import Game.Enums.typeItem;
import Game.Interfaces.*;
import Game.Reports.Reports;
import GameMenus.Menu;
import java.util.Iterator;
import java.util.Random;

public class Manual {
    private Menu menu;
    private boolean isEndTrue;
    private boolean continuarNoEdificio;
    private boolean pegouKit;
    private UnorderedListADT<String> pathDivisions;

    /**
     * Constructor for Manual Mission class.
     */
    public Manual() {
        menu = new Menu();
        isEndTrue = false;
        continuarNoEdificio = false;
        pegouKit = false;
        pathDivisions = new LinkedUnorderedList<>();
    }
    

    public void startGameManual(Game game, Mission mission)  {
        Player player = game.getPlayer();
        pathDivisions.addToRear(player.getDivision().getName());

        while (!isEndTrue) {
            informationsAboutDivision(game);

            if (!player.getDivision().getEnemysInDivision().isEmpty()) {
                menu.menuDuringFase(game, this);

                if (!player.getDivision().getEnemysInDivision().isEmpty() && !pegouKit && !continuarNoEdificio) {
                    game.updateEnemy();
                    game.getBuilding().updateConnections(player);
                    enemyAtack(player.getDivision());
                }

            } else if (player.getDivision().getEnemysInDivision().isEmpty() && !player.getDivision().getItemsInDivision().isEmpty() || player.getLife() < player.getMaxLife() && !player.getBackpack().isEmpty() || player.getDivision().isEntranceExit() && !continuarNoEdificio || player.getDivision().getTarget() != null) {
                game.updateEnemy();
                menu.menuDuringFase(game, this);

                if (!pegouKit && !continuarNoEdificio) {
                    game.updateEnemy();
                    game.getBuilding().updateConnections(player);
                }

            } else {
                pegouKit = false;
                continuarNoEdificio = false;
                game.updateEnemy();
                game.getBuilding().updateConnections(player);

                if (!player.getDivision().getEnemysInDivision().isEmpty() && !pegouKit && !continuarNoEdificio) {
                    game.updateEnemy();
                    game.getBuilding().updateConnections(player);
                    enemyAtack(player.getDivision());
                    continue;
                }

                menu.menuChangeDivision(game, player.getDivision(), this);
                pathDivisions.addToRear(player.getDivision().getName());
            }

            if (player.getLife() <= 0) {
                setIsEndTrue();
            }
        }
        if (!player.getHaveTarget()) {
            player.setLife(0);
        }
        Reports.generateReport(game, mission, this);
    }


    public void setIsEndTrue() {
        isEndTrue = true;
    }

    public void setContinuarNoEdificio() {
        continuarNoEdificio = true;
    }

    public void setPegouKit() {
        pegouKit = true;
    }

    private void enemyAtack(Division division) {
        for (Enemy enemy : division.getEnemysInDivision()) {
            enemy.atack();
        }
    }

    protected void informationsAboutDivision (Game game){
        game.informationGame();

        Iterator shortestPath = game.getBuilding().getDivisions().iteratorShortestPath(game.getPlayer().getDivision(), game.getTarget().getDivision());
        Iterator shortestPathToKit = null;
        Item itemMaisProximo = null;
        int distancia = 0;

        for (Item item : game.getItems()) {
            if (item.getDivision() == null) {
                continue;
            }

            if (item.getType() == typeItem.KIT_LIFE){
                if (itemMaisProximo == null) {
                    itemMaisProximo = item;
                    distancia = game.getBuilding().getDivisions().getDistance(game.getPlayer().getDivision(), item.getDivision());
                } else {
                    int distanciaTmp = game.getBuilding().getDivisions().getDistance(game.getPlayer().getDivision(), item.getDivision());

                    if (distanciaTmp < distancia) {
                        distancia = distanciaTmp;
                        itemMaisProximo = item;
                    }
                }
            }
        }
        if (itemMaisProximo != null) {
            shortestPathToKit = game.getBuilding().getDivisions().iteratorShortestPath(game.getPlayer().getDivision(), itemMaisProximo.getDivision());
        }

        System.out.println("\n\n----------------Shortest Path To Target---------------------");
        while (shortestPath.hasNext()) {
            Division div = (Division) shortestPath.next();
            System.out.print(div.getName());

            if (shortestPath.hasNext()) {
                System.out.print(" ---> ");
            }
        }

        if (game.getPlayer().getHaveTarget()) {
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

        if (!game.getPlayer().getDivision().getEnemysInDivision().isEmpty()) {
            for (Enemy enemy : game.getPlayer().getDivision().getEnemysInDivision()) {
                System.out.println("Enemy: " + enemy.getName() + " Life: " + enemy.getLife());
            }
        }

        if (!game.getPlayer().getDivision().getItemsInDivision().isEmpty()) {
            for (Item item : game.getPlayer().getDivision().getItemsInDivision()) {
                System.out.println("Item: " + item.getType() + " Pontos: " + item.getPoints());
            }
        }

        if (game.getPlayer().getDivision().getTarget() != null) {
            System.out.println("Tu estás na divisão do alvo ---> Alvo: " + game.getPlayer().getDivision().getTarget().getType());
        }
        
        System.out.println("Player: " + game.getPlayer().getName() + " Life: " + game.getPlayer().getLife() + " Division: " + game.getPlayer().getDivision().getName());
    }

    public UnorderedListADT<String> getPathDivisions() {
        return pathDivisions;
    }
}
