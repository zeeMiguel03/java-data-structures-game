package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Collections.Queues.LinkedQueue;
import Collections.Queues.QueueADT;
import Game.Interfaces.Division;
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Player;
import Game.Json.KeyNotFoundException;
import Game.Reports.Report;
import Game.Reports.Reports;
import GameMenus.Menu;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;

public class Manual {
    private Menu menu;
    private static boolean isEndTrue;
    private static boolean continuarNoEdificio;
    private static boolean pegouKit;
    private UnorderedListADT<String> pathDivisions;


    public Manual() throws IOException, ParseException, KeyNotFoundException {
        menu = new Menu();
        isEndTrue = false;
        continuarNoEdificio = false;
        pegouKit = false;
        pathDivisions = new LinkedUnorderedList<>();
    }

    public void startGameManual(Game game)  {
        Player player = (Player) game.getPlayer();

        while (!isEndTrue) {
            informationsAboutDivision(game);

            if (!player.getDivision().getEnemysInDivision().isEmpty() || !player.getDivision().getItemsInDivision().isEmpty() || player.getLife() < player.getMaxLife() && !player.getBackpack().isEmpty() || player.getDivision().isEntranceExit() && !continuarNoEdificio || player.getDivision().getTarget() != null && !continuarNoEdificio) {
                if (player.getLife() > 0) {
                    pegouKit = false;
                    menu.menuDuringFase(game);
                } else {
                    setIsEndTrue();
                }

                if (!player.getDivision().getEnemysInDivision().isEmpty() && !pegouKit) {
                    updateEnemy(game);
                    game.getBuilding().updateConnections(player);
                    enemyAtack(player.getDivision());
                } else if (!pegouKit) {
                    updateEnemy(game);
                    game.getBuilding().updateConnections(player);
                }
            } else {
                if (player.getLife() > 0) {
                    updateEnemy(game);
                    game.getBuilding().updateConnections(player);
                    informationsAboutDivision(game);
                    continuarNoEdificio = false;
                    pathDivisions.addToRear(player.getDivision().getName());
                    menu.menuChangeDivision(game, player.getDivision());
                } else {
                    setIsEndTrue();
                }
            }
        }
        if (!player.getHaveTarget()) {
            player.setLife(0);
        }
        Reports.generateReport(game, game.getMission(), this);
    }


    public static void setIsEndTrue() {
        isEndTrue = true;
    }

    public static void setContinuarNoEdificio() {
        continuarNoEdificio = true;
    }

    public static void setPegouKit() {
        pegouKit = true;
    }

    private void enemyAtack(Division division) {
        for (Enemy enemy : division.getEnemysInDivision()) {
            if (enemy.getLife() < 0) {
                division.removeEnemy(enemy);
            }
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
        System.out.println("--------------------------------------------------");

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
        System.out.println("Player: " + game.getPlayer().getName() + " Life: " + game.getPlayer().getLife());
    }

    public UnorderedListADT<String> getPathDivisions() {
        return pathDivisions;
    }
}
