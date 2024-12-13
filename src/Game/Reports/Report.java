package Game.Reports;

import Collections.Lists.UnorderedListADT;

/**
 * Implementation of the Report interface.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Report {

    /**
     * Return the id of the mission.
     *
     * @return the mission id
     */
    int getIdSimulation();

    /**
     * Returns the code of the mission.
     *
     * @return the mission code
     */
    String getCodMission();

    /**
     * Returns the version of the mission.
     *
     * @return the mission version
     */
    int getVersion();

    /**
     * Returns the player life of the mission.
     *
     * @return player life
     */
    int getLifePlayer();

    /**
     * Returns the path of the mission.
     *
     * @return the mission path
     */
    UnorderedListADT<String> getPath();

    /**
     * Returns the number of enemies killed.
     *
     * @return the number of killed enemies
     */
    int getEnemiesKilled();

    /**
     * Returns the number of the items picked.
     *
     * @return the number of picked items
     */
    int getItemsPicked();

    /**
     * Returns if the target is out.
     *
     * @return true if he is out, false otherwise
     */
    boolean isTargetOut();
}
