/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game.GameImpl;

import Collections.Graph.Network;
import Collections.Lists.ArrayUnorderedList;
import Game.Interfaces.GameNetworkADT;
import java.util.Iterator;

/**
 *
 * @author Miguel
 */
public class GameNetwork<T> extends Network<T> implements GameNetworkADT<T> {
    
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
    public Iterator<T> iteratorAdjacent(T vertex) {
        int index = getIndex(vertex);
        
        ArrayUnorderedList<T> adjacentVertices = new ArrayUnorderedList<>();
        
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[index][i] >= 0) {
                adjacentVertices.addToRear(vertices[i]);
            }
        }
        
        return adjacentVertices.iterator();
    } 
}
