package Game.Reports;

import Collections.Lists.UnorderedListADT;

/**
 * Implementation of the Report interface, representing a simulation report.
 *
 *
 */
public class ReportImpl implements Comparable<ReportImpl>, Report{
    private String codMission;
    private int version;
    private static int simulationManual;
    private int idSimulation;
    private UnorderedListADT<String> path;
    private int enemiesKilled;
    private int itemsPicked;
    private boolean targetOut;
    private int lifePlayer;

    /**
     * Constructor to the Report Impl class.
     *
     * @param path the report path
     * @param enemiesKilled the enemies killed
     * @param itemsPicked the item picked
     * @param targetOut if the target it out
     * @param lifePlayer the player life
     * @param codMission the code of the mission
     * @param version the version of the mission
     */
    public ReportImpl(UnorderedListADT<String> path, int enemiesKilled, int itemsPicked, boolean targetOut, int lifePlayer, String codMission, int version) {
        this.path = path;
        idSimulation = ++simulationManual;
        this.enemiesKilled = enemiesKilled;
        this.itemsPicked = itemsPicked;
        this.targetOut = targetOut;
        this.codMission = codMission;
        this.version = version;
        this.lifePlayer = lifePlayer;
    }

    /**
     * Return the id of the mission.
     *
     * @return the mission id
     */
    @Override
    public int getIdSimulation() {
        return idSimulation;
    }

    /**
     * Returns the code of the mission.
     *
     * @return the mission code
     */
    @Override
    public String getCodMission() {
        return codMission;
    }

    /**
     * Returns the version of the mission.
     *
     * @return the mission version
     */
    @Override
    public int getVersion() {
        return version;
    }

    /**
     * Returns the player life of the mission.
     *
     * @return player life
     */
    @Override
    public int getLifePlayer() {
        return lifePlayer;
    }

    /**
     * Returns the manual simulation.
     *
     * @return the manual simulation
     */
    public static int getSimulationManual() {
        return simulationManual;
    }

    /**
     * Returns the path of the mission.
     *
     * @return the mission path
     */
    @Override
    public UnorderedListADT<String> getPath() {
        return path;
    }

    /**
     * Returns the number of enemies killed.
     *
     * @return the number of killed enemies
     */
    @Override
    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    /**
     * Returns the number of the items picked.
     *
     * @return the number of picked items
     */
    @Override
    public int getItemsPicked() {
        return itemsPicked;
    }

    /**
     * Returns if the target is out.
     *
     * @return true if he is out, false otherwise
     */
    @Override
    public boolean isTargetOut() {
        return targetOut;
    }

    /**
     * Compares this ReportImpl object with another ReportImpl object based on the life of the player.
     *
     * @param other The ReportImpl object to compare with.
     * @return A negative number if this player life is greater than the other,
     *         zero if they are equal, and a positive integer if is less.
     */
    @Override
    public int compareTo(ReportImpl other) {
        return Integer.compare(other.lifePlayer, this.lifePlayer);
    }

    /**
     * String representation of the reportImpl class.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "Number Simulation: " + idSimulation + " CodMissao: " + codMission + " Version: " + version + " Player Life: " + lifePlayer + " Enemies Killed By Player: " + enemiesKilled + " Items Picked: " + itemsPicked + " Target Out successfully: " + targetOut;
    }
}
