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
public class DoublyLinkedList<T> implements ListADT<T> {
    protected DoubleNode<T> head;
    protected DoubleNode<T> tail;
    protected int count;
    protected int modCount;
    
    public void DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }

    //Funciona
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

    //Funciona
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

    //falta coisas
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException();
        }
        
        if (!contains(element)) {
            throw new ElementNotFoundException(); //corrigir esta a repetir duas vezes
        }

        T removed = null;
        
        if (count == 1) {
            removed = head.getElement();
            head = tail = null;
        } else if (tail.getElement().equals(element)) {
            removed = tail.getElement();
            tail = tail.getPrevious();
            tail.setNext(null);
        } else if (head.getElement().equals(element)) {
            removed = head.getElement();
            head = head.getNext();
            head.setPrevious(null);
        } else {
            DoubleNode<T> current = head;

            while (current != null) {
                if (current.getElement().equals(element)) {
                    removed = current.getElement();
                    current.setPrevious(current.getNext());
                }
                
                current = current.getNext();
            }
        }
            
        count--;
        modCount++;
        
        return removed;
    }

    //funciona
    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException();
        }
        
        return head.getElement();
    }

    //funciona
    @Override
    public T last() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException();
        }
        
        return tail.getElement();
    }

    //funciona
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException();
        } //usar find
        
        DoubleNode<T> current = head;
        
        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
            
            current = current.getNext();
        }
        
        return false;
    }

    //funciona
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
        private DoubleNode<T> current;
        
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
            
            okToRemove = true;
            
            return current.getNext().getElement();
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            
            DoublyLinkedList.this.remove(current.getPrevious().getElement()); //verificar pela position porque pode haver por exemplo dois 1 1
            expectedModCount++; // 1  2  3 4 5 1
            okToRemove = false;
        }
    }
    
    @Override
    public String toString() { 
        if (count == 0) {
            throw new EmptyCollectionException();
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
