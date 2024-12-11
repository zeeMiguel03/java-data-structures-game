package Game.Reports;

import Collections.Lists.ArrayOrderedList;
import Collections.Lists.DoubleLinkedOrderedList;
import Collections.Lists.OrderedListADT;
import Game.GameImpl.Game;
import Game.GameImpl.Manual;
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Mission;

import java.util.Iterator;

public class Reports {
    static OrderedListADT<Report> reports = new DoubleLinkedOrderedList<>();


    public static void generateReport(Game game, Mission mission, Manual manual) {
        Report newReport = new Report(manual.getPathDivisions(), enemiesKilled(game), itemPicked(game), game.getPlayer().getHaveTarget(), game.getPlayer().getLife(), mission.getCodMission(), mission.getVersion());

        reports.add(newReport);
    }

    private static int enemiesKilled(Game game) {
        int count = 0;

        for (Enemy enemy : game.getEnemies()) {
            if (enemy.getLife() <= 0) {
                count++;
            }
        }

        return count;
    }

    private static int itemPicked(Game game) {
        int count = 0;

        for (Item item : game.getItems()) {
            if (item.getDivision() == null) {
                count++;
            }
        }

        return count;
    }

    public static void printReports() {
        for (Report report : reports) {
            System.out.println(report);
        }
    }
}
