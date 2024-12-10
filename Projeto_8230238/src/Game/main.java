package Game;

import Game.GameImpl.Game;
import GameMenus.Menu;


public class main {
    static Menu menu;
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.loadGame();
            menu = new Menu();
            menu.mainMenu(game);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "!");
            e.printStackTrace();
        }
    }
}
