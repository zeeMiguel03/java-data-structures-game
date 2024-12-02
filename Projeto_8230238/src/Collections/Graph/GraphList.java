package Collections.Graph;

import Collections.Lists.UnorderedListADT;

import java.util.Iterator;

public class GraphList<T> implements GraphADT<T>{
    private UnorderedListADT<T> ajacencia;
    int

    @Override
    public void addVertex(T vertex) {

    }

    @Override
    public void removeVertex(T vertex) {

    }

    @Override
    public void addEdge(T vertex1, T vertex2) {

    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {

    }

    @Override
    public Iterator iteratorBFS(T startVertex) {
        return null;
    }

    @Override
    public Iterator iteratorDFS(T startVertex) {
        return null;
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
