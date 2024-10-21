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
 *
 * @author Miguel
 */
public abstract class DefaultArrayList<T> implements ListADT<T> {
    private static final int INITAL_CAPACITY = 100;
    
    protected T[] ArrayList;
    protected int count;
    protected int modCount;
    
    public DefaultArrayList(int initial) {
        this.ArrayList = (T[]) (new Object[initial]);
        this.count = 0;
        this.modCount = 0;
    }
    
    public DefaultArrayList() {
        this(INITAL_CAPACITY);
    }
       
    //funciona
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        T firstPosition = ArrayList[0];
        
        if (count > 1) { //sem if
            for (int i = 0; i < count - 1; i++) {
                ArrayList[i] = ArrayList[i + 1];
            } 
           
            ArrayList[count - 1] = null;
        } else {
            ArrayList[0] = null;
        }
       
        count--;
        modCount++;
        
        return firstPosition;
    }

    //funciona
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        T lastPosition = ArrayList[count - 1]; 
        ArrayList[count - 1] = null; //fazer aqui --
        
        count--;
        modCount++;
        
        return lastPosition;
    }

    //funciona
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
            counter++; //meter fora
        }
        
        T removed = ArrayList[counter];
                
        for (int i = counter; i < count - 1; i++) {
            ArrayList[i] = ArrayList[i + 1];
        }
        
        ArrayList[count - 1] = null;
        count--;
        modCount++;
        
        return removed;
    }

    //funciona
    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        return ArrayList[0];
    }

    //funciona
    @Override
    public T last() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list");
        }
        
        return ArrayList[count - 1];
    }

    //funciona
    @Override
    public boolean contains(T target) {                
        for (int i = 0; i < count; i++) {
            if (ArrayList[i].equals(target)) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

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
    
    protected void expandCapacity() {
        T[] expand = (T[])(new Object[count * 2]);
        
        for (int i = 0; i < ArrayList.length; i++) {
            expand[i] = ArrayList[i];
        } 
        
        ArrayList = expand;
    }
}
    