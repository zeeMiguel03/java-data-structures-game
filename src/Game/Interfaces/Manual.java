package Game.Interfaces;

import Collections.Lists.UnorderedListADT;

/**
 * A interface representing of the manual in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Manual extends Game {
    /**
     * Starts the game manually.
     */
    void startGameManual();

    /**
     * Sets the game end to true.
     */
    void setIsEndTrue();

    /**
     * Sets true that the player will remain in the building.
     */
    void setContinueInBuilding();

    /**
     * Sets true that the player has picked up a kit.
     */
    void setPickedKit();

    /**
     * returns the divisions traversed during the game.
     *
     * @return an unordered list containing the names of the divisions
     */
    UnorderedListADT<String> getPathDivisions();
}
