/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Lists;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class DoublyLinkedList<T> implements ListADT<T> {
    protected DoubleNode<T> head;
    protected DoubleNode<T> tail;
    protected int count;
    protected int modCount;
    
    /**
     * The constructor for the DoublyLinkedList
     */
    public void DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }

    /**
     * This method starts by checking if the counter is equals 0 if it is, he throws a an
     * EmptyCollectionException, otherwise he delete the first element and 
     * then returns the removed element.
     * 
     * @return the removed element
     * @throws EmptyCollectionException if the collection is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException();
        }
        
        T element = head.getElement();
        
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        
        count--;
        modCount++;
        
        return element;
    }

    /**
     * This method starts by checking if the counter is equals 0 if it is, he throws a an
     * EmptyCollectionException, otherwise he delete the last element and 
     * then returns the removed element.
     * 
     * @return the removed element
     * @throws EmptyCollectionException if the collection is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException();
        }
        
        T element = tail.getElement();
        
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        
        count--;
        modCount++;
        
        return element;
    }
    
    /**
     * This method starts by checking if the counter is equals to 0 if it is, he throws a an
     * EmptyCollectionException, then he verify if the element exists, if it isn't he throws
     * a ElementNotFoundException, otherwise he delete the element and then returns the 
     * removed element.
     * 
     * @param element the element to remove
     * @return the removed element
     * @throws EmptyCollectionException if the collection is empty
     * @throws ElementNotFoundException if the element was not found in the list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException();
        }
        
        DoubleNode<T> current = find(element);
        
        if (current == null) {
            throw new ElementNotFoundException("element not found!");
        }
        
        T removed = current.getElement();
        
        if (current == head) {
            head = head.getNext();
            head.setPrevious(null);
        } else if (current == tail) {
            tail = tail.getPrevious();
            tail.setNext(null);
        } else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
         
        count--;
        modCount++;
        
        return removed;
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
            throw new EmptyCollectionException();
        }
        
        return head.getElement();
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
            throw new EmptyCollectionException();
        }
        
        return tail.getElement();
    }

    /**
     * Verify if the element exists in the list.
     * 
     * @param target the element to search for
     * @return true if the element exists, false otherwise
     * @throws EmptyCollectionException if the collection is empty
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException();
        } 
        
        DoubleNode<T> current = find(target);
      
        return current != null;
    }
    
    /**
     * Verify if exists a certain element in the list, if it does
     * he retuns the node of the element otherwise returns false
     * 
     * @param target the element to search for
     * @return the node if it was find, false otherwise
     */
    public DoubleNode<T> find(T target) {
        DoubleNode<T> current = head;
        
        while (current != null) {
            if (current.getElement().equals(target)) {
                return current;
            }
            current = current.getNext();
        }
        
        return null;
    }

    /**
     * Verify if the list is empty.
     * @return if the list is empty return true, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the size of the list.
     * @return the list size
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
        private DoubleNode<T> current;
        
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
            
            okToRemove = true;
            T element = current.getElement();
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
            
            DoublyLinkedList.this.remove(current.getPrevious().getElement()); 
            current.getNext();
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
    public String toString() { 
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection!");
        }
        
        String result = "";
        
        DoubleNode<T> current = head;
        
        while (current != null) {
            result += " " + current.getElement();
            current = current.getNext();
        }
        
        return result;
    }
}
