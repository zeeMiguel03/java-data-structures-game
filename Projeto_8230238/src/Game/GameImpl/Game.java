/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.ExceptionsForGame.NotAnEntryExitDivisonException;
import Game.Interfaces.Building;
import Game.Interfaces.Division;
import Game.Interfaces.Mission;
import Game.Interfaces.Player;
import Game.Json.JsonHandler;
import Game.Json.KeyNotFoundException;
import GameMenus.Menu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author Miguel
 */
public class Game {
    Building building;
    Mission mission;
    UnorderedListADT<Division> divisions;
    Iterator<Division> iterator;


    public Game() {
        mission = null;
        building = new BuildingImpl();
        divisions = new LinkedUnorderedList<>();
        new LinkedUnorderedList<>();
    }

    public void setBuilding() throws IOException, ParseException, KeyNotFoundException {
        JSONArray ligacoes = (JSONArray) JsonHandler.getFromFile("ligacoes");

        createDivisions();

        for (Object ligacao : ligacoes) {
            JSONArray array = (JSONArray) ligacao;
            Division div1 = null, div2 = null;
            iterator = divisions.iterator();

            while (iterator.hasNext()) {
                Division div = iterator.next();
                if (div.getName().equals(array.get(0))) {
                    div1 = div;
                }
                if (div.getName().equals(array.get(1))) {
                    div2 = div;
                }
            }

            building.addConection(div1, div2);
        }
    }

    public Building getBuilding() {
        return building;
    }

    private int getVersion() throws IOException, ParseException, KeyNotFoundException {
        return JsonHandler.getInt("versao");
    }

    private String getCode() throws IOException, ParseException, KeyNotFoundException {
        return (String) JsonHandler.getFromFile("cod-missao");
    }

    private void createDivisions() throws IOException, ParseException, KeyNotFoundException {
        JSONArray divisionsJson = (JSONArray) JsonHandler.getFromFile("edificio");
        JSONArray entryExits = (JSONArray) JsonHandler.getFromFile("entradas-saidas");

        for (Object division : divisionsJson) {
            Division div;

            if (entryExits.contains(division)) {
                div = new DivisionImpl((String) division, true);
            } else {
                div = new DivisionImpl((String) division, false);
            }

            divisions.addToFront(div);
            building.addDivision(div);
        }
    }
}
