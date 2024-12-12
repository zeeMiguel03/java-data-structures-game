package Game;

import GameMenus.Menu;

public class    main {
    static Menu menu;
    public static void main(String[] args) {
        try {
            menu = new Menu();
            menu.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "!");
            e.printStackTrace();
        }
    }
}
