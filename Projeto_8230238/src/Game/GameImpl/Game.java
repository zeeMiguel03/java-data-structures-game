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
    Mission mission;
    private Building building;
    private UnorderedListADT<Division> divisions;
    private Iterator<Division> iterator;
    private UnorderedListADT<Item> items;
    private UnorderedListADT<Enemy> enemies;
    private Player ToCruz;
    private Target target;

    /**
     * Game class Constructor.
     */
    public Game() {
        ToCruz = new PlayerImpl(null);
        building = new BuildingImpl();
        divisions = new LinkedUnorderedList<>();
        enemies = new LinkedUnorderedList<>();
        items = new LinkedUnorderedList<>();
        target = null;

    }

    /**
     * Loads the game data.
     *
     * @throws IOException          if an I/O error occurs
     * @throws ParseException       if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    public void loadGame() throws IOException, ParseException, KeyNotFoundException {
        createDivisions();
        setEnemy();
        setBuilding();
        setAlvo();
        setItems();
        mission = new MissionImpl();
    }

    /**
     * Sets the items in the game by reading from a JSON file and placing them in the correct divisions.
     *
     * @throws IOException          if an I/O error occurs.
     * @throws ParseException       if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private void setItems() throws IOException, ParseException, KeyNotFoundException {
        JSONArray jArray = (JSONArray) JsonHandler.getFromFile("itens");
        Item mewItem;

        for (Object itens : jArray) {
            JSONObject item = (JSONObject) itens;
            long pontosVida = item.get("pontos-recuperados") != null ? (long) item.get("pontos-recuperados") : 0;
            long pontosExtra = item.get("pontos-extra") != null ? ((long) item.get("pontos-extra")) : 0;

            if (item.get("tipo").equals("kit de vida")) {
                mewItem = new ItemImpl(typeItem.KIT_LIFE, (int) pontosVida, building.searchDivisionByName(item.get("divisao").toString()));
                building.searchDivisionByName(item.get("divisao").toString()).addItem(mewItem);
            } else {
                mewItem = new ItemImpl(typeItem.VEST, (int) pontosExtra, building.searchDivisionByName(item.get("divisao").toString()));
                building.searchDivisionByName(item.get("divisao").toString()).addItem(mewItem);
            }
            items.addToRear(mewItem);
        }

    }

    /**
     * Sets the enemies in the game by reading from a JSON file and placing them in the correct divisions.
     *
     * @throws IOException          if an I/O error occurs.
     * @throws ParseException       if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private void setEnemy() throws IOException, ParseException, KeyNotFoundException {
        JSONArray jArray = (JSONArray) JsonHandler.getFromFile("inimigos");
        Enemy newEnemy;

        for (Object enemiesJson : jArray) {
            JSONObject enemy = (JSONObject) enemiesJson;
            long poder = (long) enemy.get("poder");

            for (Division division : divisions) {
                if (division.getName().equals(enemy.get("divisao").toString())) {
                    newEnemy = new EnemyImpl(enemy.get("nome").toString(), (int) poder, division, 100);
                    division.addEnemy(newEnemy);
                    enemies.addToRear(newEnemy);
                }
            }
        }
    }

    /**
     * Sets up the building layout by creating divisions and their connections.
     *
     * @throws IOException          if an I/O error occurs.
     * @throws ParseException       if a parsing error occurs.
     * @throws KeyNotFoundException if a required key is not found in the JSON data.
     */
    private void setBuilding() throws IOException, ParseException, KeyNotFoundException {
        JSONArray ligacoes = (JSONArray) JsonHandler.getFromFile("ligacoes");


        for (Object ligacao : ligacoes) {
            int weight1To2 = 0;
            int weight2To1 = 0;
            JSONArray array = (JSONArray) ligacao;
            Division div1 = null, div2 = null;
            iterator = divisions.iterator();

            while (iterator.hasNext() && div1 == null || div2 == null) {
                Division div = iterator.next();
                if (div.getName().equals(array.get(0))) {
                    div1 = div;
                }
                if (div.getName().equals(array.get(1))) {
                    div2 = div;
                }
            }
            if (!div1.getEnemysInDivision().isEmpty()) {
                for (Enemy enemy : div1.getEnemysInDivision()) {
                    if (getPlayer().getPower() > 0) {
                        weight2To1 += enemy.getPower() * ((enemy.getLife() / getPlayer().getPower()) - 1);

                    } else {
                        weight2To1 += getPlayer().getMaxLife();

                    }
                }
            }

            if (!div2.getEnemysInDivision().isEmpty()) {
                for (Enemy enemy : div2.getEnemysInDivision()) {
                    if (getPlayer().getPower() > 0) {
                        weight1To2 += enemy.getPower() * ((enemy.getLife()/ getPlayer().getPower()) - 1);

                    } else {
                        weight1To2 += getPlayer().getMaxLife();

                    }
                }
            }

            building.addConection(div1, div2, weight1To2);
            building.addConection(div2, div1, weight2To1);
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
        target = alvoImpl;
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
    
    public Player getPlayer() {
        return ToCruz;
    }

    public UnorderedListADT<Enemy> getEnemies() {
        return enemies;
    }

    public void updatePlayer(Division division) {
        if (ToCruz.getDivision() != null) {
            ToCruz.getDivision().removePlayer(ToCruz);
        }

        ToCruz.setDivision(division);
        division.addPlayer(ToCruz);
    }

    public UnorderedListADT<Item> getItems() {
        return items;
    }

    public Target getTarget() {
        return target;
    }

    public void removeEnemy(Enemy enemy) {
        Iterator enemiesIterator = enemies.iterator();
        while (enemiesIterator.hasNext()) {
            if (enemiesIterator.next().equals(enemy)) {
                enemiesIterator.remove();
            }
        }

    }

    public Mission getMission() {
        return mission;
    }
}
