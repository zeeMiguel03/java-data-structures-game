package Game.Reports;

import Collections.Lists.DoubleLinkedOrderedList;
import Collections.Lists.OrderedListADT;
import Game.GameImpl.Game;
import Game.GameImpl.Manual;
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Mission;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public static void exportPathInSimulation() {
        JSONArray reportsJson = new JSONArray();

        Iterator<Report> it = reports.iterator();

        while (it.hasNext()) {
            Report report = it.next();
            int count = 0;
            JSONArray path = new JSONArray();
            JSONObject obj = new JSONObject();
            Iterator<String> pathIterator = report.getPath().iterator();

            obj.put("NumberSimulation", report.getIdSimulation());
            obj.put("Mission", report.getCodMission());
            obj.put("MissionCompleted", report.isTargetOut());

            while (pathIterator.hasNext()) {
                JSONObject pathDivision = new JSONObject();
                String division = pathIterator.next();

                pathDivision.put(++count, division);
                path.add(pathDivision);
            }
            obj.put("Path", path);
            reportsJson.add(obj);

        }

        try (FileWriter fw = new FileWriter("dadosSimulacao.json")){
            fw.write(reportsJson.toJSONString());
            fw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
