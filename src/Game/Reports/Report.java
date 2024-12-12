package Game.Reports;

import Collections.Lists.OrderedListADT;
import Collections.Lists.UnorderedListADT;

public class Report implements Comparable<Report>{
    private String codMission;
    private int version;
    private static int simulationManual;
    private int idSimulation;
    private UnorderedListADT<String> path;
    private int enemiesKilled;
    private int itemsPicked;
    private boolean targetOut;
    private int lifePlayer;

    public Report(UnorderedListADT<String> path, int enemiesKilled, int itemsPicked, boolean targetOut, int lifePlayer, String codMission, int version) {
        this.path = path;
        idSimulation = ++simulationManual;
        this.enemiesKilled = enemiesKilled;
        this.itemsPicked = itemsPicked;
        this.targetOut = targetOut;
        this.codMission = codMission;
        this.version = version;
        this.lifePlayer = lifePlayer;
    }

    public int getIdSimulation() {
        return idSimulation;
    }

    public String getCodMission() {
        return codMission;
    }

    public int getVersion() {
        return version;
    }

    public int getLifePlayer() {
        return lifePlayer;
    }

    public static int getSimulationManual() {
        return simulationManual;
    }

    public UnorderedListADT<String> getPath() {
        return path;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public int getItemsPicked() {
        return itemsPicked;
    }

    public boolean isTargetOut() {
        return targetOut;
    }

    @Override
    public int compareTo(Report other) {
        return Integer.compare(other.lifePlayer, this.lifePlayer);
    }

    @Override
    public String toString() {
        return "Number Simulation: " + idSimulation + " CodMissao: " + codMission + " Version: " + version + " Player Life: " + lifePlayer + " Enemies Killed By Player: " + enemiesKilled + " Items Picked: " + itemsPicked + " Target Out successfully: " + targetOut;
    }
}
