/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Collections.Graph;

import java.util.Iterator;
import Collections.Exceptions.ElementNotFoundException;

/**
 *
 * @author Miguel
 */
public class TestGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Adicionar arestas
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");


        try {
            Iterator<String> path = graph.iteratorShortestPath("A", "E");
            System.out.print("Caminho mais curto de A para E: ");
            while (path.hasNext()) {
                System.out.print(path.next() + " ");
            }
        } catch (ElementNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
        }
           
            

    }
}
