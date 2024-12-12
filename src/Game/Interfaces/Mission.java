/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Game.Json.KeyNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * @author Miguel Rocha
 * @author António Monteiro
 */
public interface Mission {

    int setVersion() throws IOException, ParseException, KeyNotFoundException;

    String setCodMission() throws IOException, ParseException, KeyNotFoundException;

    /**
     * Returns the code of the mission.
     * 
     * @return the mission code
     */
    public String getCodMission();
    
    /**
     * Returns the version of the mission.
     * 
     * @return the version of the mission
     */
    public int getVersion();
    

}
