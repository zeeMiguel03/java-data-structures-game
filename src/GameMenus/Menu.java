package GameMenus;

import Game.GameImpl.ManualImpl;
import Game.Interfaces.Manual;
import Game.Interfaces.Division;
import Game.Json.KeyNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public interface Menu {
    /**
     * Menu to select and import a mission from JSON files.
     */
    void menuSelectMissionImport();

    /**
     * Menu to select the version of a mission based on the imported JSON file.
     *
     * @throws IOException if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    void menuSelectVersion() throws IOException, ParseException, KeyNotFoundException;

    /**
     * Main menu to user select what type of mission he wants to do, or se reports, export data
     * or leave the program.
     *
     * @throws IOException if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    void mainMenu() throws IOException, ParseException, KeyNotFoundException;

    /**
     * Menu to start a game in manual mode.
     *
     * @param manual the manual mission
     * @throws IOException if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    void menuStartGame(Manual manual) throws IOException, ParseException, KeyNotFoundException;

    /**
     * Menu that allows player to change division.
     *
     * @param division the actual division
     * @param manual the manual division
     */
    void menuChangeDivision(Division division, Manual manual);

    /**
     * Menu to show the diverse options that player can do during the mission.
     *
     * @param manual the manual mission
     */
    void menuDuringFase(Manual manual);
}
