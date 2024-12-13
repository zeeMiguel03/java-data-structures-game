/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Game.Interfaces;

import Collections.Graph.NetworkADT;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */
public interface GameNetworkADT<T> extends NetworkADT<T> {

    /**
     * Returns the vertex's with connection with a specific vertex.
     *
     * @param vertex the vertex to verify the connections
     * @return the connections
     */
    Iterator<T> iteratorAdjacent(T vertex);

    /**
     * Verify if two specific vertex are connected.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if they are connected, false otherwise
     */
    boolean verifyConnection(T vertex1, T vertex2);

    /**
     * Calculates the distance between two vertices.
     *
     * @param whereStartVertex the starting vertex for the search
     * @param whereEndVertex the target vertex for the search
     * @return the number of edges between the two vertices, or -1 if no path exists.
     */
    int getDistance(T whereStartVertex, T whereEndVertex);
}