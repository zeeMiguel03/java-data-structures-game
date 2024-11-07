/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NoComparableException;
import Lists.OrderedListADT;
import java.util.Iterator;



/**
 *
 * @author Miguel
 */
public class LinkedOrderedBinarySearchTree<T> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {
    
    public LinkedOrderedBinarySearchTree() {
        super();
    }
    
    public LinkedOrderedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void add(T element) throws NoComparableException {
        if (!(element instanceof Comparable)) {
            throw new NoComparableException("Element not comparable!");
        }
        
        Comparable<T> comparableElement = (Comparable<T>) element;
        
        
        
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection");
        }
        
        return removeMin();
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection");
        }
        
        return removeMax();
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection");
        }
        
        return findMin();
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty Collection");
        }
        
        return findMax();
    }

    @Override
    public Iterator<T> iterator() {
        return iteratorLevelOrder();
    }
    
}
