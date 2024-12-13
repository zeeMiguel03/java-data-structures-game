package Game.GameImpl;

import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;
import Collections.Queues.LinkedQueue;
import Collections.Queues.QueueADT;
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
import java.util.Random;

/**
 * This class implements the interface Game.
 *
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class GameImpl implements Game {
    private Mission mission;
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
    public GameImpl() {
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
    @Override
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
                        weight2To1 += (int) (enemy.getPower() * (Math.ceil((float)enemy.getLife() / getPlayer().getPower()) - 1));

                    } else {
                        weight2To1 += getPlayer().getMaxLife();
                    }
                }
            }

            if (!div2.getEnemysInDivision().isEmpty()) {
                for (Enemy enemy : div2.getEnemysInDivision()) {
                    if (getPlayer().getPower() > 0) {
                        weight1To2 += (int) (enemy.getPower() * (Math.ceil((double) enemy.getLife() / getPlayer().getPower()) - 1));

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

    /**
     * Returns the Game Building.
     *
     * @return the building
     */
    @Override
    public Building getBuilding() {
        return building;
    }

    /**
     * Returns the Game Player.
     *
     * @return the player
     */
    @Override
    public Player getPlayer() {
        return ToCruz;
    }

    /**
     * Returns the game enemies.
     *
     * @return the enemies.
     */
    @Override
    public UnorderedListADT<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Update the player position.
     *
     * @param division the player next division
     */
    @Override
    public void updatePlayer(Division division) {
        if (ToCruz.getDivision() != null) {
            ToCruz.getDivision().removePlayer(ToCruz);
        }

        ToCruz.setDivision(division);
        division.addPlayer(ToCruz);
    }

    /**
     * Return the items of the game.
     *
     * @return the items in game
     */
    @Override
    public UnorderedListADT<Item> getItems() {
        return items;
    }

    /**
     * Return the game target.
     *
     * @return the game target
     */
    @Override
    public Target getTarget() {
        return target;
    }

    /**
     * Return the game mission.
     *
     * @return the mission.
     */
    @Override
    public Mission getMission() {
        return mission;
    }

    /**
     * Represents all the information of the game by division.
     */
    @Override
    public void informationGame() {
        Iterator<Division> adj = building.getDivisions().iteratorAdjacent(getPlayer().getDivision());

        System.out.println(String.format(
                        "|--------------------------------------|\n" +
                        "|          You are here                |\n" +
                        "|              %s                      \n" +
                        "|--------------------------------------|",
                         getPlayer().getDivision().getName()));

        while (adj.hasNext()) {
            System.out.println(String.format(
                            "|--------------------------------------|\n" +
                            "|         You can go here              |\n" +
                            "|              %s                      \n" +
                            "|--------------------------------------|",
                            adj.next().getName()));
        }

        System.out.println("--------------------------------------------------\n");

        for (Enemy enemy : getEnemies()) {
            if (enemy.getLife() > 0)
                System.out.println("Enemy: " + enemy.getName() + " Life: " + enemy.getLife() + " Division: " + enemy.getDivision().getName());
        }

        for (Item item : getItems()) {
            if (item.getDivision() != null) {
                System.out.println("Item: " + item.getType() + " Pontos: " + item.getPoints() + " Division: " + item.getDivision().getName());
            }
        }

        if (getTarget() != null && !getPlayer().getHaveTarget()) {
            System.out.println("Alvo: " + getTarget().getType() + " Division: " + getTarget().getDivision().getName());
        }
    }

    /**
     * This method manages enemy movements on the build, ensuring their positions are adjusted
     * according to specific conditions.
     */
    @Override
    public void updateEnemy() {
        Random rd = new Random();
        int movimento;
        Iterator adj;
        QueueADT<Division> divisionsAdj;
        int counter;
        Division divisionEnemyToGO = null;

        for (Enemy enemy : getEnemies()) {
            if (enemy.getLife() <= 0) {
                enemy.setDivision(null);
            } else {
                if (!enemy.getDivision().equals(getPlayer().getDivision())) {
                    movimento = rd.nextInt(2);
                    if (movimento == 1) {
                        counter = 0;
                        adj = getBuilding().getDivisions().iteratorAdjacent(enemy.getDivision());
                        divisionsAdj = new LinkedQueue<>();

                        while (adj.hasNext()) {
                            Division adjDiv = (Division) adj.next();
                            if (getBuilding().getDivisions().getDistance(enemy.getInicialDivision(), adjDiv) <= 2) {
                                divisionsAdj.enqueue(adjDiv);
                                counter++;
                            }
                        }

                        int nDequeu = rd.nextInt((counter - 1) + 1) + 1;

                        for (int i = 0; i < nDequeu; i++) {
                            divisionEnemyToGO = divisionsAdj.dequeue();
                        }

                        enemy.getDivision().removeEnemy(enemy);
                        enemy.setDivision(divisionEnemyToGO);
                        divisionEnemyToGO.addEnemy(enemy);
                    }
                }
            }
        }
    }
}
