/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameMenus;

import Game.GameImpl.BuildingImpl;
import Game.GameImpl.Game;
import Game.GameImpl.MissionImpl;
import Game.Interfaces.Mission;
import Game.Json.KeyNotFoundException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Miguel
 */
public class Menu {
    private Game game;
    private Mission mission;

    public Menu() {
        this.game = new Game();
        this.mission = new MissionImpl("1", 1);
    }

    public void mainMenu() throws IOException, ParseException, KeyNotFoundException {
        int option = 0;
        
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println("----------- Menu Mission -----------");
            System.out.println("|[1] Manual Mission                |");
            System.out.println("|[2] Automatic Mission             |");
            System.out.println("|[0] Leave                         |");
            System.out.println("------------------------------------");
            System.out.print("Option: ");
            
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid character!");
                scanner.next();
                continue;
            }
            
            switch (option) {
                case 1:
                    menuStartGame();
                    break;
                case 2:  
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
            
        } while (option != 0);
        
        scanner.close();
    }

    public void menuStartGame() throws IOException, ParseException, KeyNotFoundException {
        game.setBuilding();
        int option = 0;
        int counter = 0;
        BuildingImpl build = (BuildingImpl) game.getBuilding();
        Scanner scanner = new Scanner(System.in);
        Iterator iteratorNetworkDivisions = game.getBuilding().getDivisions().iteratorBFS(build.getFirstDivision());

        do {
            System.out.println("----------- Menu Mission -----------");
            while (iteratorNetworkDivisions.hasNext()) {
                System.out.println("[" + counter++ + "] " + iteratorNetworkDivisions.next());
            }
            System.out.println("[0] Sair");
            System.out.println("------------------------------------");
            System.out.print("Option: ");

            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid character!");
                scanner.next();
                continue;
            }

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }

        } while (option != 0);

        scanner.close();

    }
}
