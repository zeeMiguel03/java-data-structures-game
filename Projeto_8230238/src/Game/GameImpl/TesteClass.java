package Game.GameImpl;

import Game.Enums.typeItem;
import Game.Enums.typeTarget;
import Game.Exceptions.DivisionNullException;
import Game.Exceptions.ItemNullException;
import Game.Exceptions.PersonNullException;
import Game.Interfaces.Building;
import Game.Interfaces.Division;
import Game.Interfaces.Enemy;
import Game.Interfaces.Item;
import Game.Interfaces.Mission;
import Game.Interfaces.Target;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe para teste das implementacoes de Division e Building. Feito pelo chatgpt
 *
 * @author Miguel
 */
public class TesteClass {

    public static void main(String[] args) {
        Building building = new BuildingImpl();
        Mission mission = new MissionImpl("1", 1, building);
        
        // Lista de divisoes do edificio
        String[] edificio = {
            "Heliporto", "Escada 6", "Camaratas", "Armazem", "Escada 5",
            "Laboratorio", "Escritorio 3", "Escada 4", "WC", "Corredor 2",
            "Seguranca", "Hall", "Escada 3", "Escritorio 1", "Escritorio 2",
            "Escada de Emergencia", "Corredor 1", "Escada 2", "Porteiro",
            "Escada 1", "Garagem"
        };

        // Divisoes de entrada-saida
        String[] entradasSaidas = {
            "Escada de Emergencia", "Garagem", "Heliporto", "Porteiro"
        };

        // Criando as divisoes
        Map<String, Division> divisionMap = new HashMap<>();
        for (String nome : edificio) {
            boolean isEntradaSaida = java.util.Arrays.asList(entradasSaidas).contains(nome);
            Division division = new DivisionImpl(nome, isEntradaSaida);
            divisionMap.put(nome, division);
            try {
                building.addDivision(division);
            } catch (DivisionNullException e) {
                System.err.println("Erro ao adicionar divisao: " + e.getMessage());
            }
        }

        // Adicionando as conexoes entre divisoes
        String[][] ligacoes = {
            {"Garagem", "Escada 1"},
            {"Garagem", "Escada de Emergencia"},
            {"Escritorio 1", "Escada de Emergencia"},
            {"Porteiro", "Escada 1"},
            {"Porteiro", "Escada 2"},
            {"Corredor 1", "Escada 2"},
            {"Corredor 1", "Escritorio 1"},
            {"Corredor 1", "Escritorio 2"},
            {"Corredor 1", "Escada 3"},
            {"Hall", "Escada 3"},
            {"Hall", "Seguranca"},
            {"Corredor 2", "Seguranca"},
            {"Corredor 2", "WC"},
            {"Corredor 2", "Escada 4"},
            {"Escritorio 3", "Escada 4"},
            {"Escritorio 3", "Escada 5"},
            {"Laboratorio", "Escada 5"},
            {"Armazem", "Escada 5"},
            {"Camaratas", "Escada 5"},
            {"Camaratas", "Escada 6"},
            {"Heliporto", "Escada 6"}
        };

        for (String[] ligacao : ligacoes) {
            Division division1 = divisionMap.get(ligacao[0]);
            Division division2 = divisionMap.get(ligacao[1]);
            try {
                building.addConection(division1, division2);
            } catch (DivisionNullException e) {
                System.err.println("Erro ao adicionar conexao: " + e.getMessage());
            }
        }

        // Adicionando os itens
        String[][] itens = {
            {"WC", "20", "kit de vida"},
            {"Escritorio 1", "15", "kit de vida"},
            {"Escada 2", "25", "colete"}
        };

        for (String[] itemData : itens) {
            Division division = divisionMap.get(itemData[0]);
            int points = Integer.parseInt(itemData[1]);
            typeItem itemType = itemData[2].equals("kit de vida") ? typeItem.KIT_LIFE : typeItem.VEST;
            Item item = new ItemImpl(itemType, points, division);
            try {
                division.addItem(item);
            } catch (ItemNullException e) {
                System.err.println("Erro ao adicionar item: " + e.getMessage());
            }
        }

        // Adicionando os inimigos
        String[][] inimigos = {
            {"badguy1", "5", "Heliporto"},
            {"badguy2", "15", "Heliporto"},
            {"badguy3", "20", "Camaratas"},
            {"badguy4", "15", "Seguranca"},
            {"badguy5", "15", "Corredor 1"}
        };

        for (String[] inimigoData : inimigos) {
            String name = inimigoData[0];
            int power = Integer.parseInt(inimigoData[1]);
            Division division = divisionMap.get(inimigoData[2]);
            Enemy enemy = new EnemyImpl(name, power, division, 100);  // Assuming life is 100 for all enemies
            try {
                division.addEnemy(enemy);
            } catch (PersonNullException e) {
                System.err.println("Erro ao adicionar inimigo: " + e.getMessage());
            }
        }

        // Adicionando o alvo
        Target target = new TargetImpl(typeTarget.CHEMICAL, divisionMap.get("Laboratorio"));
        divisionMap.get("Laboratorio").setTarget(target);

        // Simulating the mission
        mission.manualSimulation();
    }
}
