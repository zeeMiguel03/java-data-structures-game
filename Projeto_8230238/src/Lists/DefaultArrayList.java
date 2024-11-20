/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;


/**
 * @author Miguel Rocha
 */
public abstract class DefaultArrayList<T> implements ListADT<T> {
    private static final int INITIAL_CAPACITY = 100;
    
    protected T[] ArrayList;
    protected int count;
    protected int modCount;
    
    /**
     * Creates an new DefaultArrayList with a specific initial capacity.
     * @param initial the initial capacity
     */
    public DefaultArrayList(int initial) {
        this.ArrayList = (T[]) (new Object[initial]);
        this.count = 0;
        this.modCount = 0;
    }
    
    /**
     * Creates an new DefaultArrayList with the default initial capacity.
     */
    public DefaultArrayList() {
        this(INITIAL_CAPACITY);
    }
       
    /**
     * This method strats by checking if the counter is equals 0 if it is, he throws a an
     * EmptyCollectionException, otherwise the method shifts the elements to the right place, 
     * and delete the first element and then returns the removed element.
     * 
     * @return the removed element
     * @throws EmptyCollectionException if the collection is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        T firstPosition = ArrayList[0];
        
        for (int i = 0; i < count - 1; i++) {
            ArrayList[i] = ArrayList[i + 1];
        } 
           
        ArrayList[count - 1] = null;

        count--;
        modCount++;
        
        return firstPosition;
    }

    /**
     * This method strats by checking if the counter is equals 0 if it is, he throws a an
     * EmptyCollectionException, the method shifts the elements to the right place, 
     * and delete the last element and then returns the removed element.
     * 
     * @return the removed element
     * @throws EmptyCollectionException if the collection is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        T lastPosition = ArrayList[count - 1]; 
        
        ArrayList[--count] = null; 
        modCount++;
        
        return lastPosition;
    }

    /**
     * This method starts by checking if the counter is equals to 0 if it is, he throws a an
     * EmptyCollectionException, then he calls the method getElementIndex, to get the element index 
     * to remove, if the index is null he throws an ElementNotFoundException, otherwise
     * the method shifts the elements to the right place, and delete the element
     * and then returns the removed element.
     * 
     * @param element the element to remove
     * @return the removed element
     * @throws EmptyCollectionException if the collection is empty
     * @throws ElementNotFoundException if the element was not found in the ArrayList
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        int index = getElementIndex(element);
        
        if (ArrayList[index] == null) {
            throw new ElementNotFoundException("Element not found!");
        }
        
        T removed = ArrayList[index];
                
        for (int i = index; i < count - 1; i++) {
            ArrayList[i] = ArrayList[i + 1];
        }
        
        ArrayList[--count] = null;
        modCount++;
        
        return removed;
    }
    
    /**
     * This method search a element in the Arraylist and return the element position.
     * 
     * @param element the element to search
     * @return the element position
     */
    private int getElementIndex(T element) {
        int counter = 0;
        
        while (counter < count && !ArrayList[counter].equals(element)) {
            counter++; 
        }
            
        return counter;
    }

    /**
     * This method returns the first element of the list if it is not empty.
     * 
     * @return the first element
     * @throws EmptyCollectionException if list was empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        return ArrayList[0];
    }

    /**
     * This method returns the last element of the list if it is not empty.
     * 
     * @return the last element
     * @throws EmptyCollectionException if list was empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        return ArrayList[count - 1];
    }

    /**
     * This method search for a specific element in the ArrayList.
     * 
     * @param target the element to search for
     * @return true if the element was found, false otherwise
     */
    @Override
    public boolean contains(T target) {                
        for (int i = 0; i < count; i++) {
            if (ArrayList[i].equals(target)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * This method verify if the ArrayList is empty.
     * 
     * @return true if is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * This method return the size of the ArrayList.
     * 
     * @return the size.
     */
    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>();
    }
    
    private class BasicIterator<E> implements Iterator<T> {
        private int expectedModCount;
        private boolean okToRemove;
        private int current;
        
        public BasicIterator() {
            this.expectedModCount = modCount;
            this.okToRemove = false;
            this.current = 0;
        }
        
        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            
            if (!hasNext()) {
                throw new ElementNotFoundException();
            }
            
            okToRemove = true;
            
            return ArrayList[current++];
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            
            DefaultArrayList.this.remove(ArrayList[current - 1]);
            current--;
            expectedModCount++;
            okToRemove = false;
        }
    }
    
    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public String toString() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        String list = "";
        
        for (int i = 0; i < count ; i++) {
            list += " " + ArrayList[i];
        }
        
        return list;
    }
    

    /**
     * Expands the capacity of the ArrayList.
     */
    protected void expandCapacity() {
        T[] expand = (T[])(new Object[count * 2]);
        
        for (int i = 0; i < ArrayList.length; i++) {
            expand[i] = ArrayList[i];
        } 
        
        ArrayList = expand;
    }
}
    