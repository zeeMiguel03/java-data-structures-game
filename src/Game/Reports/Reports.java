package Game.Reports;

import Collections.Lists.DoubleLinkedOrderedList;
import Collections.Lists.OrderedListADT;
import Game.GameImpl.GameImpl;
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Manual;
import Game.Interfaces.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Reports {
    static OrderedListADT<ReportImpl> reports = new DoubleLinkedOrderedList<>();

    /**
     * Create a Report
     * @param manual where the information about the path, player and game are
     */
    public static void generateReport(Manual manual) {
        ReportImpl newReport = new ReportImpl(manual.getPathDivisions(), enemiesKilled(manual), itemPicked(manual), manual.getPlayer().getHaveTarget(), manual.getPlayer().getLife(), manual.getMission().getCodMission(), manual.getMission().getVersion());

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

    /**
     *
     * @param game, where the informations are
     * @return the number of items the player picked
     */
    private static int itemPicked(Game game) {
        int count = 0;

        for (Item item : game.getItems()) {
            if (item.getDivision() == null) {
                count++;
            }
        }

        return count;
    }

    /**
     * print the reports created in generateReport
     */
    public static void printReports() {
        for (ReportImpl report : reports) {
            System.out.println(report);
        }
    }

    /**
     * creates a json file, where will show the informations about the path, Number of the simulation, Mission and if the mission was completed
     */
    public static void exportPathInSimulation() {
        JSONArray reportsJson = new JSONArray();

        Iterator<ReportImpl> it = reports.iterator();

        while (it.hasNext()) {
            ReportImpl report = it.next();
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
