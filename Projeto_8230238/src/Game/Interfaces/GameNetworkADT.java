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
     * Returns the vertexs with connection with a specific vertex.
     *
     * @param vertex the vertex to verify the connections
     * @return the connections
     */
    public Iterator<T> iteratorAdjacent(T vertex);

    /**
     * Verify if two specific vertex are connected.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if they are connected, false otherwise
     */
    public boolean verifyConnection(T vertex1, T vertex2);
}