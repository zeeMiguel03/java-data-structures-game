/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Graph.Network;
import Collections.Lists.ArrayUnorderedList;
import Collections.Lists.UnorderedListADT;
import Game.Interfaces.GameNetworkADT;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 * @author AntÃ³nio Monteiro
 */
public class GameNetwork<T> extends Network<T> implements GameNetworkADT<T> {

    /**
     * GameNetwork constructor.
     */
    public GameNetwork() {
        super();
    }

    /**
     * Returns the vertexs with connection with a specific vertex.
     *
     * @param vertex the vertex to verify the connections
     * @return the connections
     */
    @Override
    public Iterator<T> iteratorAdjacent(T vertex) { //podemos fazer isto certo? ou nao podemos usar o iterator??
        int index = getIndex(vertex);

        if (!indexIsValid(index)) {
            return new ArrayUnorderedList<T>().iterator();
        }

        UnorderedListADT<T> adjacentVertices = new ArrayUnorderedList<>();

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[index][i] != Double.POSITIVE_INFINITY) {
                adjacentVertices.addToRear(vertices[i]);
            }
        }

        return adjacentVertices.iterator();
    }

    /**
     * Verify if two specific vertex are connected.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if they are connected, false otherwise
     */
    @Override
    public boolean verifyConnection(T vertex1, T vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (!indexIsValid(index1) || !indexIsValid(index2)) {
            throw new ElementNotFoundException("Vertex not found!");
        }

        return adjMatrix[index1][index2] != Double.POSITIVE_INFINITY;
    }
}