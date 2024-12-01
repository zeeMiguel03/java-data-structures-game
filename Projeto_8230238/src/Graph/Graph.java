/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graph;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Lists.ArrayUnorderedList;
import Queues.LinkedQueue;
import Stacks.LinkedStack;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */

/**
 * Graph represents an adjacency matrix implementation of a graph.
 */
public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 2;
    protected int numVertices; 
    protected boolean[][] adjMatrix; 
    protected T[] vertices; 
    
    /**
     * Creates a empty graph.
     */
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph
     * if necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        
        vertices[numVertices] = vertex;
        
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        
        numVertices++;
    }

    /**
     * Removes a single vertex with the given value from this graph.
     * 
     * @param vertex the vertex to be removed from this graph
     * @throws EmptyCollectionException if the colletion is empty
     */
    @Override
    public void removeVertex(T vertex) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("No vertex's!");
        }
        
        int index = getIndex(vertex);
        
        if (index == -1) {
            throw new ElementNotFoundException("Vertex not found!");
        }
        
        for (int i = index; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
        }
        
        vertices[numVertices - 1] = null;
        numVertices--;
        
        for (int i = index; i < numVertices - 1; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }
        
        for (int j = index; j < numVertices - 1; j++) {
            for (int i = 0; i < numVertices; i++) {
                adjMatrix[i][j] = adjMatrix[i][j + 1]; 
            }
        }    
    }
    
    /**
     * Search for a specific vertex and return his index.
     * 
     * @param vertex the vertex to search
     * @return the index of the vertex if it was found, -1 otherwise.
     */
    protected int getIndex(T vertex) { 
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Inserts an edge between two vertices of the graph.
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }
    
    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge (int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Removes an edge between two vertices of this graph.
     * 
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws ElementNotFoundException if the collection is not found
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
         
        if (index1 == -1 || index2 == -1) {
             throw new ElementNotFoundException("element not found");
        }
        
        adjMatrix[index1][index2] = false;
        adjMatrix[index2][index1] = false;
    }
    
    /**
     * Returns an iterator that performs a breadth first search
     * traversal.
     * 
     * @param startVertex the vertex to begin the search from
     * @return an iterator that performs a breadth first traversal
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }
    
    /**
     * Returns an iterator that performs a breadth first search
     * traversal starting at the given index.
     *
     * @param startVertex the index to begin the search from
     * @return an iterator that performs a breadth first traversal
     */
    private Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x]);

            /**Find all vertices adjacent to x that have 
               not been visited and queue them up*/
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        
        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a depth first search
     * traversal starting at the given vertex.
     *
     * @param startVertex the vertex to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }
    
    /**
     * Returns an iterator that performs a depth first search
     * traversal starting at the given index.
     *
     * @param startIndex the index to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     */
    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            /**Find a vertex adjacent to x that has not been visited 
             * and push it on the stack */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }
    
    /**
     * Verify if the index is valid.
     * 
     * @param index the index to verify
     * @return true if is valid, false otherwise
     */
    protected boolean indexIsValid(int index) {
        return ((index < numVertices) && (index >= 0));
    }

    /**
     * Returns true if this graph is empty, false otherwise.
     *
     * @return true if this graph is empty
     */
    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    /**
     * Verify if the graph is connected.
     * 
     * @return true if the graph was connected, false otherwise
     * @throws EmptyCollectionException if the graph was empty
     */
    @Override
    public boolean isConnected() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("No vertex's!");
        }
        
        Iterator<T> iterator = iteratorBFS(0);
        int count = 0;

        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        
        return count == numVertices;
    }

    /**
     * Return the number of vertices.
     * 
     * @return vertices number
     */
    @Override
    public int size() {
        return numVertices;
    } 
    
    /**
     * Expands the capacity of the Graph.
     */
    protected void expandCapacity() {
        T[] expandVert = (T[])(new Object[vertices.length * 2]);
        boolean[][] expandMatrix = new boolean[vertices.length * 2][vertices.length * 2];
        
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

        result += "   "; 
        
        for (int i = 0; i < numVertices; i++) {
            if (i < 10) {
                result += " " + i + " "; 
            } else {
                result += i + " "; 
            }
        }
        
        result += "\n";

        result += "   ";
        
        for (int i = 0; i < numVertices; i++) {
            result += "---";
        }
        
        result += "\n";

        for (int i = 0; i < numVertices; i++) {
            if (i < 10) {
                result += " " + i + "|"; 
            } else {
                result += i + "|"; 
            }

            for (int j = 0; j < numVertices; j++) {
                result += " " + (adjMatrix[i][j] ? "1" : "0") + " ";
            }
            
            result += "\n";
        }

        return result;
    }
}
