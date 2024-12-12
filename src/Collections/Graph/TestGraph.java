/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Collections.Graph;

import java.util.Iterator;

/**
 *
 * @author Miguel
 */
public class TestGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GraphADT<Integer> graph = new Network<>();
        
        try {
            graph.addVertex(0);
            graph.addVertex(1);
            graph.addVertex(2);
            graph.addVertex(3);
            graph.addVertex(4);
            graph.addVertex(5);
            
            graph.addEdge(0,1);
            graph.addEdge(1, 2);

            
            
            System.out.println("BFS (Iniciando no vértice 0): ");
            Iterator<Integer> bfsIterator = graph.iteratorBFS(0);
            while (bfsIterator.hasNext()) {
                System.out.print(bfsIterator.next() + " ");
            }
            
           
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
