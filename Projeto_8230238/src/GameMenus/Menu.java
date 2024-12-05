/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameMenus;

import Game.Interfaces.Mission;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Miguel
 */
public class Menu {
    private Mission mission;
        
    public void mainMenu() {
        int option = 0;
        
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.println("----------- Menu Mission -----------");
            System.out.println("|[1] Manual Mission                |");
            System.out.println("|[2] Automatic Mission             |");
            System.out.println("|[3] Import data                   |");
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
                    mission.manualSimulation();
                    break;
                case 2:
                    mission.automaticSimulation();
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
