package Game.GameImpl;

import Game.Exceptions.DivisionNullException;
import Game.Interfaces.Building;
import Game.Interfaces.Division;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe para teste das implementações de Division e Building. Feito pelo chatgpt
 *
 * @author Miguel
 */
public class TesteClass {

    public static void main(String[] args) {
        // Instância do Building
        Building building = new BuildingImpl();
        
        // Lista de divisões do edifício
        String[] edificio = {
            "Heliporto", "Escada 6", "Camaratas", "Armazém", "Escada 5",
            "Laboratório", "Escritório 3", "Escada 4", "WC", "Corredor 2",
            "Segurança", "Hall", "Escada 3", "Escritório 1", "Escritório 2",
            "Escada de Emergência", "Corredor 1", "Escada 2", "Porteiro",
            "Escada 1", "Garagem"
        };

        // Divisões de entrada-saída
        String[] entradasSaidas = {
            "Escada de Emergência", "Garagem", "Heliporto", "Porteiro"
        };

        // Criando as divisões
        Map<String, Division> divisionMap = new HashMap<>();
        for (String nome : edificio) {
            boolean isEntradaSaida = java.util.Arrays.asList(entradasSaidas).contains(nome);
            Division division = new DivisionImpl(nome, isEntradaSaida);
            divisionMap.put(nome, division);
            try {
                building.addDivision(division);
            } catch (DivisionNullException e) {
                System.err.println("Erro ao adicionar divisão: " + e.getMessage());
            }
        }

        // Adicionando as conexões entre divisões
        String[][] ligacoes = {
            {"Garagem", "Escada 1"},
            {"Garagem", "Escada de Emergência"},
            {"Escritório 1", "Escada de Emergência"},
            {"Porteiro", "Escada 1"},
            {"Porteiro", "Escada 2"},
            {"Corredor 1", "Escada 2"},
            {"Corredor 1", "Escritório 1"},
            {"Corredor 1", "Escritório 2"},
            {"Corredor 1", "Escada 3"},
            {"Hall", "Escada 3"},
            {"Hall", "Segurança"},
            {"Corredor 2", "Segurança"},
            {"Corredor 2", "WC"},
            {"Corredor 2", "Escada 4"},
            {"Escritório 3", "Escada 4"},
            {"Escritório 3", "Escada 5"},
            {"Laboratório", "Escada 5"},
            {"Armazém", "Escada 5"},
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
                System.err.println("Erro ao adicionar conexão: " + e.getMessage());
            }
        }
        
        building.printEntranceExit();
    }
}
