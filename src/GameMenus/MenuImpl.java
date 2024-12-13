/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameMenus;

import Collections.Queues.LinkedQueue;
import Collections.Queues.QueueADT;
import Game.Enums.typeItem;
import Game.GameImpl.*;
import Game.Interfaces.*;
import Game.Json.JsonHandler;
import Game.Json.KeyNotFoundException;
import Game.Reports.Reports;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */

public class MenuImpl implements Menu{

    public Scanner scanner = new Scanner(System.in);

    /**
     * Menu to select and import a mission from JSON files.
     */
    @Override
    public void menuSelectMissionImport() {
        int count = 0;
        int option = 0;

        do {
            File folder = new File("Json");
            File[] files = folder.listFiles();

            if (files != null && files.length > 0) {
                System.out.println("----------Select a file:-----------------");
                for (File file : files) {
                    System.out.println("[" + (count + 1) + "]" + ". " + file.getName());
                }

                System.out.println("------------------------------------");
                System.out.print("Option: ");
            } else {
                System.out.println("No files found.");
                return;
            }

            try {
                option = scanner.nextInt();

                if (option < 1 || option > files.length) {
                    System.out.println("Invalid option!");
                } else {
                    String file = files[option - 1].getName();
                    JsonHandler.setFile(file);
                    System.out.println("File selected: " + file);
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid option!");
                scanner.next();
            }

        } while (true);
    }

    /**
     * Menu to select the version of a mission based on the imported JSON file.
     *
     * @throws IOException if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    @Override
    public void menuSelectVersion() throws IOException, ParseException, KeyNotFoundException {
        int option = 0;

        do {
            option = 0;

            System.out.println("-----------" + JsonHandler.getFromFile("cod-missao") + "-----------");
            System.out.println("[1] Version " + JsonHandler.getInt("versao"));
            System.out.println("------------------------------------");
            System.out.print("Option: ");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option!");
                menuSelectVersion();
                scanner.next();
            }

            switch (option) {
                case 1:
                    Reports.printReports();
            }

        }while (option < 0 || option > 1);
    }

    /**
     * Main menu to user select what type of mission he wants to do, or se reports, export data
     * or leave the program.
     *
     * @throws IOException if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    @Override
    public void mainMenu() throws IOException, ParseException, KeyNotFoundException {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            option = 0;

            System.out.println("----------- Menu Mission -----------");
            System.out.println("|[1] Manual Mission                |");
            System.out.println("|[2] Automatic Mission             |");
            System.out.println("|[3] Reports                       |");
            System.out.println("|[4] Export Data                   |");
            System.out.println("|[0] Leave Game                    |");
            System.out.println("------------------------------------");
            System.out.print("Option: ");
            
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option!");
                mainMenu();
                scanner.next();
            }
            
            switch (option) {
                case 1:
                    Manual manual = new ManualImpl();
                    menuStartGame(manual);
                    break;
                case 2:
                    Automatic automatic = new AutomaticImpl();
                    automatic.startGameAutomatic();
                    break;
                case 3:
                    menuSelectVersion();
                case 4:
                    Reports.exportPathInSimulation();
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
            
        } while (option != 0);
        
        scanner.close();
    }

    /**
     * Menu to start a game in manual mode.
     *
     * @param manual the manual mission
     * @throws IOException if an I/O error occurs
     * @throws ParseException if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    @Override
    public void menuStartGame(Manual manual) throws IOException, ParseException, KeyNotFoundException {
        int option = 0;
        QueueADT<Division> divisoes;
        Division division = null;
        int nDivisoesInDivision = 0;
        QueueADT<Division> divisionsEntranceExit;

        do {
            divisionsEntranceExit = manual.getBuilding().getEntranceExit();
            divisoes = new LinkedQueue<>();
            int divisionsInQueue = divisionsEntranceExit.size();
            int count = 0;

            System.out.println("----------- Menu Mission -----------");
            
            for (int i = 0; i < divisionsInQueue; i++) {
                Division divisionInQueue = divisionsEntranceExit.dequeue();
                divisoes.enqueue(divisionInQueue);
                System.out.println("[" + ++count + "] " + divisionInQueue.getName());
            }
            
            System.out.println("[0] Leave Game");
            System.out.println("------------------------------------");
            System.out.print("Option: ");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid character!");
                menuStartGame(manual);
                scanner.next();
            }

            switch (option) {
                case 0:
                    System.out.println("Leaving...");
                    break;
            }

            nDivisoesInDivision = divisoes.size();
            if (option > 0 && option <= divisoes.size()) {
                for (int i = 0; i < option; i++) {
                    division = divisoes.dequeue();
                }
                manual.updatePlayer(division);
                manual.startGameManual();
            } else {
                System.out.println("Invalid option. Please select a valid option.");
            }

        } while (option < 0 || option > nDivisoesInDivision);
    }

    /**
     * Menu that allows player to change division.
     *
     * @param division the actual division
     * @param manual the manual division
     */
    @Override
    public void menuChangeDivision(Division division, Manual manual) {
        int option = 0;

        QueueADT<Division> divisoes;
        int divisoesSize = 0;
        
        do {
            System.out.println("----------- Menu Mission -----------");
            
            divisoes = manual.getBuilding().printNextDivisions(division);
            
            System.out.println("[0] Leave Game");
            System.out.println("------------------------------------");
            System.out.print("Option: ");
            
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid character!");
                menuChangeDivision(division, manual);
                scanner.next();
            }


            divisoesSize = divisoes.size();
            if (option > 0 && option <= divisoesSize) {
                for (int i = 0; i < option; i++) {
                    division = divisoes.dequeue();
                }

                manual.updatePlayer(division);
                
            } else if (option == 0) {
                manual.setIsEndTrue();
                System.out.println("Leaving...");

            } else {
                System.out.println("Invalid option. Please select a valid option.");
            }

            
        } while (option < 0 || option > divisoesSize);

    }

    /**
     * Menu to show the diverse options that player can do during the mission.
     *
     * @param manualImpl the manual mission
     */
    @Override
    public void menuDuringFase(Manual manualImpl) {
        int option = 0;
        int counter;
        Division divPlayer;
        Player player;
        QueueADT<String> opcoes;

        do {
            divPlayer = manualImpl.getPlayer().getDivision();
            player =  manualImpl.getPlayer();
            counter = 0;
            opcoes = new LinkedQueue<>();
            System.out.println("----------- Menu Mission -----------");

            if (!divPlayer.getEnemysInDivision().isEmpty()) {
                System.out.println("[" + ++counter + "] Atacar Inimigos!");
                opcoes.enqueue("ataque");
            }

            if (!divPlayer.getItemsInDivision().isEmpty()) {
                boolean itemColete = false;
                boolean itemKit = false;
                for (Item item : divPlayer.getItemsInDivision()) {
                    if (item.getType() == typeItem.VEST) {
                        itemColete = true;
                    }
                    if (item.getType() == typeItem.KIT_LIFE) {
                        itemKit = true;
                    }
                }

                if (itemColete) {
                    System.out.println("[" + ++counter + "] Equipar colete!");
                    opcoes.enqueue("colete");
                } else if (itemKit) {
                    System.out.println("[" + ++counter + "] Pegar Kit!");
                    opcoes.enqueue("pegar");
                }
            }

            if (player.getLife() < player.getMaxLife() && !player.getBackpack().isEmpty()) {
                System.out.println("[" + ++counter + "] Usar Kit Medico");
                opcoes.enqueue("kit");
            }

            if (divPlayer.isEntranceExit() && player.getDivision().getEnemysInDivision().isEmpty()) {
                System.out.println("[" + ++counter + "] Sair do edificio/Missao!");
                opcoes.enqueue("sair");
                System.out.println("[" + ++counter + "] Continuar no edificio!");
                opcoes.enqueue("continuar");
            }

            if(divPlayer.getTarget() != null && !player.getHaveTarget() && divPlayer.getEnemysInDivision().isEmpty()) {
                System.out.println("[" + ++counter + "] Pegar Alvo!");
                opcoes.enqueue("alvo");
            }

            System.out.println("[0] Leave Game");
            System.out.println("------------------------------------");
            System.out.print("Option: ");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid character!");
                menuDuringFase(manualImpl);
                scanner.next();
            }

            if (option == 0) {
                manualImpl.setIsEndTrue();
            }

            String opcao = null;
            if (option <= counter && option > 0) {
                for (int i = 0; i < option; i++) {
                    opcao = opcoes.dequeue();
                }
            }

            if (opcao != null) {
                switch (opcao) {
                    case "alvo":
                        player.setHaveTarget();
                        player.getDivision().getTarget().setDivision(null);
                        player.getDivision().setTarget(null);
                        break;

                    case "continuar":
                        manualImpl.setContinueInBuilding();
                        break;

                    case "colete":
                        player.useVest();
                        break;

                    case "ataque":
                        player.atack();
                        break;

                    case "pegar":
                        player.pickItem();
                        manualImpl.setPickedKit();
                        break;

                    case "kit":
                        player.useMedicKit();
                        break;

                    case "sair":
                        manualImpl.setIsEndTrue();
                        break;

                }
            }

        } while (option < 0 || option > counter);
    }
}
