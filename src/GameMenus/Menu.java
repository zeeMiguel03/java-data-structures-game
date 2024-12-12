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
import Game.Json.KeyNotFoundException;
import Game.Reports.Reports;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author Miguel
 */
public class Menu {

    Scanner scanner = new Scanner(System.in);


    public Menu() {
    }

    public void mainMenu() throws IOException, ParseException, KeyNotFoundException {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            Game game = new Game();
            game.loadGame();
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
                    menuStartGame(game, true);
                    break;
                case 2:  
                    game.startGame( false);
                    break;
                case 3:
                    Reports.printReports();
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

    public void menuStartGame(Game game, boolean isManualOption) throws IOException, ParseException, KeyNotFoundException {
        boolean isManual = isManualOption;
        int option = 0;
        BuildingImpl build;
        QueueADT<Division> divisoes;
        Division division = null;
        int nDivisoesInDivision = 0;
        do {
            build = (BuildingImpl) game.getBuilding();
            System.out.println("----------- Menu Mission -----------");
            
            divisoes = build.printEntranceExit();
            
            System.out.println("[0] Leave Game");
            System.out.println("------------------------------------");
            System.out.print("Option: ");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid character!");
                menuStartGame(game, isManualOption);
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
                game.updatePlayer(division);
                game.startGame(isManual);
            } else {
                System.out.println("Invalid option. Please select a valid option.");
            }

        } while (option < 0 || option > nDivisoesInDivision);
    }

    public void menuChangeDivision(Game game, Division division, Manual manual) {
        int option = 0;

        QueueADT<Division> divisoes;
        int divisoesSize = 0;
        
        do {
            System.out.println("----------- Menu Mission -----------");
            
            divisoes = game.getBuilding().printNextDivisions(division);
            
            System.out.println("[0] Leave Game");
            System.out.println("------------------------------------");
            System.out.print("Option: ");
            
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid character!");
                menuChangeDivision(game, division, manual);
                scanner.next();
            }


            divisoesSize = divisoes.size();
            if (option > 0 && option <= divisoesSize) {
                for (int i = 0; i < option; i++) {
                    division = divisoes.dequeue();
                }

                game.updatePlayer(division);
                
            } else if (option == 0) {
                manual.setIsEndTrue();
                System.out.println("Leaving...");

            } else {
                System.out.println("Invalid option. Please select a valid option.");
            }

            
        } while (option < 0 || option > divisoesSize);

    }

    public void menuDuringFase(Game game, Manual manual) {
        int option = 0;
        int counter;
        Division divPlayer;
        Player player;
        QueueADT<String> opcoes;

        do {
            divPlayer = game.getPlayer().getDivision();
            player =  (Player) game.getPlayer();
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
                menuDuringFase(game, manual);
                scanner.next();
            }

            if (option == 0) {
                manual.setIsEndTrue();
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
                        manual.setContinuarNoEdificio();
                        break;

                    case "colete":
                        player.useVest();
                        break;

                    case "ataque":
                        player.atack();
                        break;

                    case "pegar":
                        player.pickItem();
                        manual.setPegouKit();
                        break;

                    case "kit":
                        player.useMedicKit();
                        break;

                    case "sair":
                        manual.setIsEndTrue();
                        break;

                }
            }


        } while (option < 0 || option > counter);
    }
}
