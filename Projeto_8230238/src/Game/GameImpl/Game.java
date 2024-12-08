/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Enums.typeItem;
import Game.Interfaces.*;
import Game.Json.JsonHandler;
import Game.Json.KeyNotFoundException;
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

    public void loadGame() throws IOException, ParseException, KeyNotFoundException {
        setBuilding();
        setItems();
        setEnemy();
    }

    public void setItems() throws IOException, ParseException, KeyNotFoundException {
        JSONArray jArray = (JSONArray) JsonHandler.getFromFile("itens");
        Item mewItem;

        for (Object itens : jArray) {
            JSONObject item = (JSONObject) itens;
            long pontosVida = item.get("pontos-recuperados") != null ? (long) item.get("pontos-recuperados") : 0;
            long pontosExtra = item.get("pontos-extra") != null ? ((long)item.get("pontos-extra")) : 0;

            if (item.get("tipo").equals("kit de vida")) {
                mewItem = new ItemImpl(typeItem.KIT_LIFE, (int)pontosVida, building.searchDivisionByName(item.get("divisao").toString()));
                building.searchDivisionByName(item.get("divisao").toString()).addItem(mewItem);
            } else {
                mewItem = new ItemImpl(typeItem.VEST, (int)pontosExtra, building.searchDivisionByName(item.get("divisao").toString()));
                building.searchDivisionByName(item.get("divisao").toString()).addItem(mewItem);
            }
        }

    }

    public void setEnemy() throws IOException, ParseException, KeyNotFoundException {
        JSONArray jArray = (JSONArray) JsonHandler.getFromFile("inimigos");
        Person newEnemy;

        for (Object enemies : jArray) {
            JSONObject enemy = (JSONObject) enemies;
            long poder = (long) enemy.get("poder");

            newEnemy = new EnemyImpl(enemy.get("nome").toString(), (int) poder, building.searchDivisionByName(enemy.get("divisao").toString()), 100);
            building.searchDivisionByName(enemy.get("divisao").toString()).addPerson(newEnemy);
        }

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

    public void startGame(Boolean isManual, Division divisionPlayer) {
        Person ToCruz = new PlayerImpl(divisionPlayer);
        building.searchDivisionByName(divisionPlayer.getName()).addPerson(ToCruz);

        if (isManual) {
            return;
        } else {
            return;
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
