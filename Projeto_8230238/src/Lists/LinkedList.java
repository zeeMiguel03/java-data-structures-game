/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Stacks.LinearNode;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */
public class LinkedList<T> implements ListADT<T> {
    protected LinearNode<T> head, tail;
    protected int count, modCount;

    /**
     * Empty linked list constructor.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }

    /**
     * This method starts by verifying if the collection is empty, if
     * it was he throws a EmptyCollectionException, otherwise he
     * removes and returns the first element of the list.
     *
     * @return the removed element
     * @throws EmptyCollectionException if the collection was empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T removed = head.getElement();
        
        head = head.getNext();
        
        count--;
        modCount++;
        
        return removed;
    }

    /**
     * This method starts by verifying if the collection is empty, if
     * it was he throws a EmptyCollectionException, otherwise he
     * removes and returns the last element of the list.
     *
     * @return the removed element
     * @throws EmptyCollectionException if the list was empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T removed = tail.getElement();
        
        LinearNode<T> current = head;
        
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        
        current.setNext(null);
        tail = current;
        
        count--;
        modCount++;
        
        return removed;
    }

    /**
     * This method starts by verifying if the collection is empty, if
     * it was he throws a EmptyCollectionException, otherwise starts searching
     * for the specific element in the list, if the element was not found he
     * throws a ElementNotFoundException, otherwise he removes the element
     * was return the element.
     *
     * @param element the element to be removed from the list
     * @return the removed element
     * @throws EmptyCollectionException if the collection was empty
     * @throws ElementNotFoundException if the element was not found
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        LinearNode<T> current = head;
        LinearNode<T> previous = null;
        
        while (current != null && !current.getElement().equals(element)) {
            previous = current;
            current = current.getNext();
        }
        
        if (current != tail && !current.getElement().equals(element)) {
            throw new ElementNotFoundException("Element not found!");
        }
        
        if (current == head) {
            return removeFirst();
        } else if (current == tail) {
            return removeLast();
        }
        
        T removed = current.getElement();

        previous.setNext(current.getNext());

        count--;
        modCount++;
        
        return removed;   
    }

    /**
     * Returns the first element of the list.
     *
     * @return the first element
     * @throws EmptyCollectionException if the list was empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        return head.getElement();
    }

    /**
     * Returns the last element of the list.
     *
     * @return the last element
     * @throws EmptyCollectionException if the list was empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        return tail.getElement();
    }

    /**
     * Search for a specific element in a list.
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains the element, false otherwise
     * @throws EmptyCollectionException if the list was empty
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        LinearNode<T> current = head;
        
        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
            
            current = current.getNext();
        }
        
        return false;
    }

    /**
     * Verify if the list is Empty.
     * @return true if the list was empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Verify the size of the list.
     * @return the size of the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns the iterator.
     * @return the iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>();
    }
    
    /**
     * Creates a new iterator for the list
     */
    private class BasicIterator<E> implements Iterator<T> { 
        private int expectedModCount;
        private boolean okToRemove;
        private LinearNode<T> current;
        
        /**
         * constructor for the iterator
         */
        public BasicIterator() {
            this.expectedModCount = modCount;
            this.okToRemove = false;
            this.current = head;
        }

        /**
         * Verify if there is other element
         * @return true if there is, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element of the list
         * @return the next element
         */
        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            
            if (!hasNext()) {
                throw new ElementNotFoundException();
            }
            
            T element = current.getElement();
            okToRemove = true;
            current = current.getNext();
            
            return element;
        }

        /**
         * Removes the last element returned by the iterator
         */
        @Override
        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            
            LinkedList.this.remove(current.getElement());
            expectedModCount++;
            
            okToRemove = false;
        }
    }

    /**
     * Returns a string representation of the list.
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        String result = "";
        
        LinearNode<T> current = head;
        
        while (current != null) {
            result += " " + current.getElement();
            current = current.getNext();
        }
        
        return result;
    }
}
