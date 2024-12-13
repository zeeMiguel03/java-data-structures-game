/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Game.Interfaces.*;
import Game.Json.JsonHandler;
import Game.Json.KeyNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 *  A class representing a mission in the game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class MissionImpl implements Mission {
    private String codMission;
    private int version;

    /**
     * Creates a new MissionImpl class.
     * 
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    public MissionImpl() throws IOException, ParseException, KeyNotFoundException {
        version = setVersion();
        codMission = setCodMission();
    }

    /**
     * Sets the version of the mission by retrieving it from a JSON file.
     *
     * @return the version of the mission as an integer.
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private int setVersion() throws IOException, ParseException, KeyNotFoundException {
        return JsonHandler.getInt("versao");
    }

    /**
     * Gets the mission code from the JSON data.
     *
     * @return the mission code.
     * @throws IOException          if an I/O error occurs.
     * @throws ParseException       if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private String setCodMission() throws IOException, ParseException, KeyNotFoundException {
        return (String) JsonHandler.getFromFile("cod-missao");
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
     * @return the version of the mission
     */
    @Override
    public int getVersion() {
        return version;
    }
}