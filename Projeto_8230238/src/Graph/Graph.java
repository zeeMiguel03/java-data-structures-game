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
public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
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

    @Override
    public void removeVertex(T vertex) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("No vertex's!");
        }
        
        int index = findIndex(vertex);
        
        if (index == -1) {
            throw new ElementNotFoundException("Vertex not found!");
        }
        
        for (int i = index; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
            
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
                adjMatrix[j][i] = adjMatrix[j][i + 1];
            }
        }
        
        vertices[numVertices - 1] = null;
        numVertices--;
    }
    
    private int findIndex(T vertex) { 
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        
        return -1;
    }
    
    @Override
    public void addEdge(T vertex1, T vertex2) {
        int index1 = findIndex(vertex1);
        int index2 = findIndex(vertex2);
         
        if (index1 == -1 || index2 == -1) {
            throw new ElementNotFoundException("element not found");
        }
         
        adjMatrix[index1][index2] = true;
        adjMatrix[index2][index1] = true;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int index1 = findIndex(vertex1);
        int index2 = findIndex(vertex2);
         
        if (index1 == -1 || index2 == -1) {
             throw new ElementNotFoundException("element not found");
        }
        
        adjMatrix[index1][index2] = false;
        adjMatrix[index2][index1] = false;
    }
    
    @Override
    public Iterator iteratorBFS(T startVertex) {
        return iteratorBFS(findIndex(startVertex));
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
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

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

    @Override
    public Iterator iteratorDFS(T startVertex) {
        return iteratorDFS(findIndex(startVertex));
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
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private boolean indexIsValid(int index) {
        return ((index < numVertices) && (index >= 0));
    }

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    @Override
    public boolean isConnected() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("No vertex's!");
        }
        
        Iterator<T> iterator = iteratorBFS(0);
        int count = 0;

        while (iterator.hasNext()) {
            count++;
        }
        
        return count == numVertices;
    }

    @Override
    public int size() {
        return numVertices;
    } 
    
    private void expandCapacity() {
        T[] expandVert = (T[])(new Object[vertices.length * 2]);
        boolean[][] expandMatrix = new boolean[vertices.length * 2][vertices.length * 2];
        
        for (int i = 0; i < numVertices; i++) {
            expandVert[i] = vertices[i];
            
            for (int j = 0; j < numVertices; j++) {
                expandMatrix[i][j] = adjMatrix[i][j];
            }
        }
        
        vertices = expandVert;
        expandMatrix = adjMatrix;
    }
}
