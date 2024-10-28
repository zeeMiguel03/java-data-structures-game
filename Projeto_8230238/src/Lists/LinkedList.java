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
 *
 * @author Miguel
 */
public class LinkedList<T> implements ListADT<T> {
    private LinearNode<T> head, tail;
    private int count, modCount;
    
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }
    
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        
        if (count == 0) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        
        count++;
        modCount++;
    }

    //funciona
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

    //funciona
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

    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        return head.getElement();
    }
    
    @Override
    public T last() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        return tail.getElement();
    }

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
        private LinearNode<T> current;
        
        public BasicIterator() {
            this.expectedModCount = modCount;
            this.okToRemove = false;
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

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
    
    public LinearNode<T> firstLink() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        return head;
    }
    
    
    public String print(LinearNode node) {
        String result = "";
        
        if (node == null) {
            result = "";
        } else {
            result += node.getElement() + " " + print(node.getNext());
        }
        
        return result;
    } 
}
