/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Graph;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Lists.ArrayUnorderedList;
import Collections.Queues.LinkedQueue;
import Collections.Stacks.LinkedStack;
import Collections.Trees.PriorityQueue;

import java.util.Iterator;


/**
 * Network represents an adjacency matrix implementation of a network.
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {
    protected double[][] adjMatrix;

    /**
     * Creates an empty network
     */
    public Network() {
        this.numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
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
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
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
     * Inserts an edge between two vertices of the graph, with a specific weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight from the edge
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (index1 == -1 || index2 == -1) {
            throw new ElementNotFoundException("One or both vertices not found.");
        }

        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        }
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(vertex1, vertex2, 0);
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

        adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
        adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
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
     * @param startIndex the index to begin the search from
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
                if (adjMatrix[x][i] < Double.POSITIVE_INFINITY && !visited[i]) {
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
                if (adjMatrix[x][i] < Double.POSITIVE_INFINITY && !visited[i]) {
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



    /**
     * Returns an iterator that performs a traversal of the shortest path
     * between two vertices, starting at the given vertex.
     *
     * @param startVertex the vertex to begin the traversal from
     * @param targetVertex the target vertex
     * @return an iterator that performs a traversal of the shortest path
     */
    protected Iterator<T> iteratorShortestPathNet(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (startIndex == -1 || targetIndex == -1) {
            throw new ElementNotFoundException("One or both vertices not found.");
        }

        double[] distances = new double[numVertices];
        int[] previous = new int[numVertices];
        boolean[] visited = new boolean[numVertices];


        for (int i = 0; i < numVertices; i++) {
            distances[i] = Double.POSITIVE_INFINITY;
            previous[i] = -1;
            visited[i] = false;
        }


        distances[startIndex] = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.addElement(startIndex, 0);

        while (!priorityQueue.isEmpty()) {
            int current = priorityQueue.removeNext();
            visited[current] = true;

            if (current == targetIndex) {
                break;
            }

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[current][i] >= 0 && !visited[i]) {
                    double newDist = distances[current] + adjMatrix[current][i];

                    if (newDist < distances[i]) {
                        distances[i] = newDist;
                        previous[i] = current;
                        priorityQueue.addElement(i, (int) newDist);
                    }
                }
            }
        }

        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        if (distances[targetIndex] == Double.POSITIVE_INFINITY) {
            return resultList.iterator();
        }

        LinkedStack<T> pathStack = new LinkedStack<>();
        int current = targetIndex;
        while (current != -1) {
            pathStack.push(vertices[current]);
            current = previous[current];
        }

        while (!pathStack.isEmpty()) {
            resultList.addToRear(pathStack.pop());
        }

        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a traversal of the shortest path
     * between two vertices, starting at the given vertex.
     *
     * @param startVertex the vertex to begin the traversal from
     * @param targetVertex the target vertex
     * @return an iterator that performs a traversal of the shortest path
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPathNet(startVertex,targetVertex);
    }

    /**
     * Returns the value of the short path, between two vertex.
     *
     * @param startVertex the start vertex
     * @param targetVertex the finish vertex
     * @return the weight of the shortest.
     */
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) {
        double result = 0;
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<T> it = iteratorShortestPath(startVertex, targetVertex);

        if (it.hasNext()) {
            index1 = getIndex(it.next());
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = getIndex(it.next());
            result += adjMatrix[index1][index2];
            index1 = index2;
        }

        return result;
    }

    /**
     * Expands the capacity of the Network.
     */
    @Override
    protected void expandCapacity() {
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
            if (i < 10) {
                result += " " + i + "  ";
            } else {
                result += i + " ";
            }
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
