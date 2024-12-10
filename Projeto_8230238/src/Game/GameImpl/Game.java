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
    private UnorderedListADT<Division> divisions;
    private Iterator<Division> iterator;
    private UnorderedListADT<Enemy> enemies;
    private Person ToCruz;

    /**
     * Game class Constructor.
     */
    public Game() {
        ToCruz = new PlayerImpl(null);
        building = new BuildingImpl();
        divisions = new LinkedUnorderedList<>();
        enemies = new LinkedUnorderedList<>();
    }

    /**
     * Loads the game data.
     * 
     * @throws IOException if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    public void loadGame() throws IOException, ParseException, KeyNotFoundException {
        setBuilding();
        setItems();
        setEnemy();
        setAlvo();
    }

    /**
     * Sets the items in the game by reading from a JSON file and placing them in the correct divisions.
     * 
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
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

    /**
     * Sets the enemies in the game by reading from a JSON file and placing them in the correct divisions.
     * 
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private void setEnemy() throws IOException, ParseException, KeyNotFoundException {
        JSONArray jArray = (JSONArray) JsonHandler.getFromFile("inimigos");
        Person newEnemy;

        for (Object enemiesJson : jArray) {
            JSONObject enemy = (JSONObject) enemiesJson;
            long poder = (long) enemy.get("poder");

            newEnemy = new EnemyImpl(enemy.get("nome").toString(), (int) poder, building.searchDivisionByName(enemy.get("divisao").toString()), 100);
            building.searchDivisionByName(enemy.get("divisao").toString()).addPerson(newEnemy);
            enemies.addToRear((Enemy) newEnemy);
        }

    }

    /**
     * Sets up the building layout by creating divisions and their connections.
     * 
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
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

    /**
     * Sets the target in the game by reading from a JSON file and placing it in the correct division.
     * 
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
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

    /**
     * Gets the version of the game from the JSON data.
     * 
     * @return the version number.
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private int getVersion() throws IOException, ParseException, KeyNotFoundException {
        return JsonHandler.getInt("versao");
    }

    /**
     * Gets the mission code from the JSON data.
     * 
     * @return the mission code.
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private String getCode() throws IOException, ParseException, KeyNotFoundException {
        return (String) JsonHandler.getFromFile("cod-missao");
    }

    /**
     * Creates the divisions by reading from the JSON file and adding them to the building.
     * 
     * @throws IOException if an I/O error occurs.
     * @throws ParseException if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
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
    
    public void startGame(Boolean isManual) throws IOException, ParseException, KeyNotFoundException {

        if (isManual) {
            Manual manual = new Manual();
            manual.startGameManual(this);
        } else {
            return;
        }
    }

    public Building getBuilding() {
        return building;
    }
    
    public Person getPlayer() {
        return ToCruz;
    }

    public UnorderedListADT<Enemy> getEnemies() {
        return enemies;
    }

    public void updatePlayer(Division division) {
        if (ToCruz.getDivision() != null) {
            ToCruz.getDivision().removePerson(ToCruz);
        }

        ToCruz.setDivision(division);
        division.addPerson(ToCruz);
    }
}
