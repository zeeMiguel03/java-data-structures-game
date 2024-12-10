package Game.GameImpl;

import Collections.Lists.UnorderedListADT;
import Game.Interfaces.Enemy;
import Game.Interfaces.Player;
import Game.Json.KeyNotFoundException;
import GameMenus.Menu;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Manual {
    private Game game;
    private Menu menu;
    private static boolean isEndTrue;
    private static boolean continuarNoEdificio;
    private static boolean pegouKit;


    public Manual() throws IOException, ParseException, KeyNotFoundException {
        menu = new Menu();
        isEndTrue = false;
        continuarNoEdificio = false;
        pegouKit = false;
    }

    public void startGameManual(Game game)  {
        Player player = (Player) game.getPlayer();

        while (!isEndTrue) {
            if (!player.getDivision().getEnemysInDivision().isEmpty() || !player.getDivision().getItemsInDivision().isEmpty() || player.getLife() < 100 && !player.getBackpack().isEmpty() || player.getDivision().isEntranceExit() && !continuarNoEdificio){
                if (!player.getDivision().getEnemysInDivision().isEmpty() && !pegouKit) {
                    for (Enemy enemy : player.getDivision().getEnemysInDivision()) {
                        System.out.println("Enemy: " + enemy.getName() + " Life: " + enemy.getLife());
                        if (enemy.getLife() < 0) {
                            player.getDivision().removeEnemy(enemy);
                        }
                        enemy.atack();
                    }
                }
                System.out.println("Player: " + player.getName() + " Life: " + player.getLife());
                if (player.getLife() > 0) {
                    pegouKit = false;
                    menu.menuDuringFase(game);
                } else {
                    setIsEndTrue();
                }
            }
            else {
                continuarNoEdificio = false;
                menu.menuChangeDivision(game,player.getDivision());
        }   }
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
}
