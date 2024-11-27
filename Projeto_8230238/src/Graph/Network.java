/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;


/**
 * @author Miguel Rocha
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {
    protected double[][] adjMatrix;
    
    public Network() {
        this.numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
    }
    
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }
    
    private void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        }
    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Expands the capacity of the Graph.
     */
    private void expandCapacity() {
        T[] expandVert = (T[])(new Object[vertices.length * 2]);
        double[][] expandMatrix = new double[vertices.length * 2][vertices.length * 2];
        
        for (int i = 0; i < numVertices; i++) {
            expandVert[i] = vertices[i];
            
            for (int j = 0; j < numVertices; j++) {
                expandMatrix[i][j] = adjMatrix[i][j];
            }
        }
        
        vertices = expandVert;
        adjMatrix = expandMatrix;
    }
    
    /**
     * String representation of the graph.
     * 
     * @return string representation of the graph
     */
    @Override
    public String toString() {
        String result = "";

        result += "    ";

        for (int i = 0; i < numVertices; i++) {
            result += i + "   ";
        }

        result += "\n";

        result += "     ";

        for (int i = 0; i < numVertices; i++) {
            result += "----";
        }
        result += "\n";

        for (int i = 0; i < numVertices; i++) {
            result += i + " | "; 

            for (int j = 0; j < numVertices; j++) {
                result += (int) adjMatrix[i][j] + "   "; 
            }
            result += "\n";
        }

        return result;
    }
}
