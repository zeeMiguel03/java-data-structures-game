/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 *
 * @author Miguel
 */
public class DefaultArrayList<T> implements ListADT<T> {
    private static final int INITAL_CAPACITY = 100;
    private T[] ArrayList;
    private int count;
    
    public DefaultArrayList() {
        this.ArrayList = (T[]) (new Object[INITAL_CAPACITY]);
        this.count = 0;
    }   
    
    public DefaultArrayList(int initial) {
        this.ArrayList = (T[]) (new Object[initial]);
        this.count = 0;
    }

    /**
    * Removes and returns the first element from this list.
    * @return the first element from this list
    */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        T firstPosition = ArrayList[0];
        
        if (count > 1) {
            for (int i = 0; i < count - 1; i++) {
                ArrayList[i] = ArrayList[i + 1];
            } 
           
            ArrayList[count - 1] = null;
        } else {
            ArrayList[0] = null;
        }
       
        count--;
        
        return firstPosition;
    }

    /**
    * Removes and returns the last element from this list.
    * @return the last element from this list
    */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        T lastPosition = ArrayList[count - 1]; 
        ArrayList[count - 1] = null;
        
        count--;
        
        return lastPosition;
    }

    /**
    * Removes and returns the specified element from this list.
    * @param element the element to be removed from the list
    */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        if (!contains(element)) {
            throw new ElementNotFoundException("Element not found!");
        }
        
        int counter = 0;
        
        while (counter < count && !ArrayList[counter].equals(element)) {
            counter++;
        }
        
        T removed = ArrayList[counter];
        
        for (int i = counter; counter < count - 1; i++) {
            ArrayList[i] = ArrayList[i + 1];
        }
        
        ArrayList[count - 1] = null;
        
        return removed;
    }

    /**
    * Returns a reference to the first element in this list.
    * @return a reference to the first element in this list
    */
    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        return ArrayList[0];
    }

    /**
    * Returns a reference to the last element in this list.
    * @return a reference to the last element in this list
    */
    @Override
    public T last() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        return ArrayList[count - 1];
    }

    /**
    * Returns true if this list contains the specified target
    * element.
    * @param target the target that is being sought in the list
    * @return true if the list contains this element
    */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        for (T element : ArrayList) {
            if (element.equals(target)) {
                return true;
            }
        }
        
        return false;
    }

    /**
    * Returns true if this list contains no elements.
    * @return true if this list contains no elements
    */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
    * Returns the number of elements in this list.
    *
    * @return the integer representation of number of
    * elements in this list
    */
    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
    