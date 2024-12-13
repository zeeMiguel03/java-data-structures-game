package Game;

import GameMenus.Menu;
import GameMenus.MenuImpl;

public class main {
    static Menu menu;

    public static void main(String[] args) {
        try {
            menu = new MenuImpl();
            menu.menuSelectMissionImport();
            menu.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "!");
        }
    }
}
