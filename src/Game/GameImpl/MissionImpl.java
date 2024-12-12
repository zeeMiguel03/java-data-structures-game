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
 * @author Miguel Rocha
 * @author António Monteiro
 */
public class MissionImpl implements Mission {
    private String codMission;
    private int version;

    /**
     * Creates a new MissionImpl class.
     * 
     * @throws IOException
     * @throws ParseException
     * @throws KeyNotFoundException 
     */
    public MissionImpl() throws IOException, ParseException, KeyNotFoundException {
        version = setVersion();
        codMission = setCodMission();
    }

    @Override
    public int setVersion() throws IOException, ParseException, KeyNotFoundException {
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
    @Override
    public String setCodMission() throws IOException, ParseException, KeyNotFoundException {
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