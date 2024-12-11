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
                    updateEnemy(game);
                    game.getBuilding().updateConnections(player);
                    enemyAtack(player.getDivision());
                }

            } else if (player.getDivision().getEnemysInDivision().isEmpty() && !player.getDivision().getItemsInDivision().isEmpty() || player.getLife() < player.getMaxLife() && !player.getBackpack().isEmpty() || player.getDivision().isEntranceExit() && !continuarNoEdificio || player.getDivision().getTarget() != null) {
                menu.menuDuringFase(game, this);


                if (!pegouKit && !continuarNoEdificio) {
                    updateEnemy(game);
                    game.getBuilding().updateConnections(player);
                }
            } else {
                pegouKit = false;
                continuarNoEdificio = false;
                updateEnemy(game);
                game.getBuilding().updateConnections(player);
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

    private void updateEnemy(Game game) {
        Random rd = new Random();
        int movimento;
        Iterator adj;
        QueueADT<Division> divisionsAdj;
        int counter;
        Division divisionEnemyToGO = null;

        for (Enemy enemy : game.getEnemies()) {
            if (enemy.getLife() <= 0) {
                enemy.setDivision(null);
            } else {
                if (!enemy.getDivision().equals(game.getPlayer().getDivision())) {
                    movimento = rd.nextInt(2);
                    if (movimento == 1) {
                        counter = 0;
                        adj = game.getBuilding().getDivisions().iteratorAdjacent(enemy.getDivision());
                        divisionsAdj = new LinkedQueue<>();

                        while (adj.hasNext()) {
                            Division adjDiv = (Division) adj.next();
                            if (game.getBuilding().getDivisions().getDistance(enemy.getInicialDivision(), adjDiv) <= 2) {
                                divisionsAdj.enqueue(adjDiv);
                                counter++;
                            }
                        }

                        int nDequeu = rd.nextInt((counter - 1) + 1) + 1;

                        for (int i = 0; i < nDequeu; i++) {
                            divisionEnemyToGO = divisionsAdj.dequeue();
                        }

                        enemy.getDivision().removeEnemy(enemy);
                        enemy.setDivision(divisionEnemyToGO);
                        divisionEnemyToGO.addEnemy(enemy);
                    }
                }
            }
        }
    }

    private void informationsAboutDivision (Game game){
        System.out.println("--------------------------------------------------");
        for (Enemy enemy : game.getEnemies()) {
            if (enemy.getLife() > 0)
                System.out.println("Enemy: " + enemy.getName() + " Life: " + enemy.getLife() + " Division: " + enemy.getDivision().getName());
        }

        for (Item item : game.getItems()) {
            if (item.getDivision() != null) {
                System.out.println("Item: " + item.getType() + " Pontos: " + item.getPoints() + " Division: " + item.getDivision().getName());
            }
        }

        if (game.getTarget() != null && !game.getPlayer().getHaveTarget()) {
            System.out.println("Alvo: " + game.getTarget().getType() + " Division: " + game.getTarget().getDivision().getName());
        }

        System.out.println("\n\n----------------Shortest Path---------------------");
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

        System.out.print("Shortest Path to Target: ");
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

        System.out.print("\nShortest Path to kit: ");
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
