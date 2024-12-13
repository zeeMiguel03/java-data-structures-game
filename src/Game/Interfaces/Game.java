package Game.Interfaces;

import Collections.Lists.UnorderedListADT;
import Game.Json.KeyNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Game defines the interface to a game implementation.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Game {

    /**
     * Loads the game data.
     *
     * @throws IOException          if an I/O error occurs
     * @throws ParseException       if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    void loadGame() throws IOException, ParseException, KeyNotFoundException;

    /**
     * Returns the Game Building.
     *
     * @return the building
     */
    Building getBuilding();

    /**
     * Returns the Game Player.
     *
     * @return the player
     */
    Player getPlayer();

    /**
     * Returns the game enemies.
     *
     * @return the enemies.
     */
    UnorderedListADT<Enemy> getEnemies();

    /**
     * Update the player position.
     *
     * @param division the player next division
     */
    void updatePlayer(Division division);

    /**
     * Return the items of the game.
     *
     * @return the items in game
     */
    UnorderedListADT<Item> getItems();

    /**
     * Return the game target.
     *
     * @return the game target
     */
    Target getTarget();

    /**
     * Return the game mission.
     *
     * @return the mission.
     */
    Mission getMission();

    /**
     * Represents all the information of the game by division.
     */
    void informationGame();

    /**
     * This method manages enemy movements on the build, ensuring their positions are adjusted
     * according to specific conditions.
     */
    void updateEnemy();
}
