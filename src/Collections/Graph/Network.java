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
import Collections.Trees.LinkedHeap;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */

/**
 * Network represents an adjacency matrix implementation of a network.
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {
    protected double[][] adjMatrix;

    /**
     * Creates a empty network
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
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * Inserts an edge between two vertices of the graph, with a specific weight.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    /**
     * Inserts an edge between two vertices of the graph, with a specific weight.
     *
     * @param index1 the first vertex
     * @param index2 the second vertex
     * @param weight the weight from the edge
     */
    private void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
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
     * Returns the shortest path between two vertices.
     *
     * This code is adapted from a repository by João Oliveira.
     *
     * @Author João Oliveira
     * @Source https://github.com/joaopsoliveira03
     * Repository: https://github.com/joaopsoliveira03-school/estg-ed/blob/main/src/main/java/Collections/Graphs/Network.java
     *
     * @param vertex1 the starting vertex
     * @param vertex2 the target vertex
     * @return the shortest path between the vertices
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        return shortestPathWeight(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Returns an iterator that performs a traversal of the indices
     * of the vertices along the shortest path between two vertices,
     * starting at the given vertex.
     *
     * @Author João Oliveira
     * @Source https://github.com/joaopsoliveira03
     * Repository: https://github.com/joaopsoliveira03-school/estg-ed/blob/main/src/main/java/Collections/Graphs/Network.java
     *
     * @param startIndex the index of the vertex to begin the traversal from
     * @param targetIndex the index of the target vertex
     * @return an iterator that performs a traversal of the indices of
     *         the vertices along the shortest path
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;

        //Update the pathWeight for each vertex except the startVertex. Notice
        //that all vertices not adjacent to the startVertex will have a
        //pathWeight of infinity for now
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            weight = traversalMinHeap.removeMin();
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                visited[index] = true;
            }

            //Update the pathWeight for each vertex that has not been
            //visited and is adjacent to the last vertex that was visited.
            //Also, add each unvisited vertex to the heap
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);

        while (it.hasNext()) {
            templist.addToRear(vertices[it.next()]);
        }

        return templist.iterator();
    }

    /**
     * Returns an iterator that performs a traversal of the shortest path
     * between two vertices, starting at the given vertex.
     *
     * @Author João Oliveira
     * @Source https://github.com/joaopsoliveira03
     * Repository: https://github.com/joaopsoliveira03-school/estg-ed/blob/main/src/main/java/Collections/Graphs/Network.java
     *
     *
     * @param startVertex the vertex to begin the traversal from
     * @param targetVertex the target vertex
     * @return an iterator that performs a traversal of the shortest path
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex),getIndex(targetVertex));
    }

    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited, double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] < Double.POSITIVE_INFINITY) && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;  // should never get to here
    }

    public double shortestPathWeight(int startIndex, int targetIndex) {
        double result = 0;

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);

        if (it.hasNext()) {
            index1 = it.next();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = it.next();
            result += adjMatrix[index1][index2];
            index1 = index2;
        }

        return result;
    }

    /**
     * Expands the capacity of the Graph.
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
