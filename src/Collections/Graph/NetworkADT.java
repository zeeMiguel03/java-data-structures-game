/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Collections.Graph;

/**
 * NetworkADT defines the interface to a network.
 */
public interface NetworkADT<T> extends GraphADT<T> {
    
    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     */
    public void addEdge (T vertex1, T vertex2, double weight);

    double shortestPathWeight(T startVertex, T targetVertex);
}
