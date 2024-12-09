/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Enums.typeItem;
import Game.Enums.typeTarget;
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
    private Building building;
    private Mission mission;
    private UnorderedListADT<Division> divisions;
    private Iterator<Division> iterator;
    private Person ToCruz;


    public Game() {
        mission = null;
        ToCruz = null;
        building = new BuildingImpl();
        divisions = new LinkedUnorderedList<>();
        new LinkedUnorderedList<>();
    }

    public void loadGame() throws IOException, ParseException, KeyNotFoundException {
        setBuilding();
        setItems();
        setEnemy();
        setAlvo();
    }

    private void setItems() throws IOException, ParseException, KeyNotFoundException {
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

    private void setEnemy() throws IOException, ParseException, KeyNotFoundException {
        JSONArray jArray = (JSONArray) JsonHandler.getFromFile("inimigos");
        Person newEnemy;

        for (Object enemies : jArray) {
            JSONObject enemy = (JSONObject) enemies;
            long poder = (long) enemy.get("poder");

            newEnemy = new EnemyImpl(enemy.get("nome").toString(), (int) poder, building.searchDivisionByName(enemy.get("divisao").toString()), 100);
            building.searchDivisionByName(enemy.get("divisao").toString()).addPerson(newEnemy);
        }

    }

    private void setBuilding() throws IOException, ParseException, KeyNotFoundException {
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

    private void setAlvo() throws IOException, ParseException, KeyNotFoundException {
        JSONObject alvo = (JSONObject) JsonHandler.getFromFile("alvo");
        Division alvoDivision = building.searchDivisionByName((String) alvo.get("divisao"));
        Target alvoImpl = null;

        switch ((String) alvo.get("tipo")) {
            case "quimico":
                alvoImpl = new TargetImpl(typeTarget.CHEMICAL, alvoDivision);
                break;
            case "gun":
                alvoImpl = new TargetImpl(typeTarget.GUN, alvoDivision);
                break;
            case "person":
                alvoImpl = new TargetImpl(typeTarget.PERSON, alvoDivision);
                break;
        }

        alvoDivision.setTarget(alvoImpl);
    }

    public void startGame(Boolean isManual, Division divisionPlayer) {
        ToCruz = new PlayerImpl(divisionPlayer);
        divisionPlayer.addPerson(ToCruz);

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
