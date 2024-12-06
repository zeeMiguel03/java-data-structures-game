package Game;

import Game.Json.KeyNotFoundException;
import GameMenus.Menu;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class main {
    static Menu menu = new Menu();

    public static void main(String[] args) {
        try {
            menu.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
