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


    public Manual() throws IOException, ParseException, KeyNotFoundException {
        menu = new Menu();
        isEndTrue = false;
    }

    public void startGameManual(Game game)  {
        Player player = (Player) game.getPlayer();

        while (!isEndTrue) {
            if (!player.getDivision().getEnemysInDivision().isEmpty() || !player.getDivision().getItemsInDivision().isEmpty() || player.getLife() < 100 && !player.getBackpack().isEmpty() || player.getDivision().isEntranceExit())
                menu.menuDuringFase(game);
            else
                menu.menuChangeDivision(game,player.getDivision());
        }
    }

    public static void setIsEndTrue() {
        isEndTrue = true;
    }
}
