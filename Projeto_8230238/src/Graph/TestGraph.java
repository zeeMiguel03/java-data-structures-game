/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Graph;

/**
 *
 * @author Miguel
 */
public class TestGraph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NetworkADT<Integer> graph = new Network<>();
        
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        
        graph.addEdge(1, 2, 3);
        
        System.out.println(graph.toString());
    }
    
}
