/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Lists;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 *
 * @author Miguel
 */
public class CircularDoubleLinkedList<T> implements ListADT<T> {
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int count;
    
    public void add(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        
        if (count == 0) {
            head = tail = newNode;
            head.setNext(tail);
            tail.setPrevious(newNode);
        } else {
            tail.setNext(newNode);
            newNode.setNext(head);
            newNode.setPrevious(tail);
            tail = newNode;
            head.setPrevious(newNode);
        }
                        
        count++;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T removed = head.getElement();
        
        head = head.getNext();
        head.setPrevious(tail);
        tail.setNext(head);
        
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T removed = head.getElement();
        
        tail = tail.getPrevious();
        tail.setNext(head);
        head.setPrevious(tail);
        
        return removed;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        DoubleNode<T> current = head;
        
        while (current != tail && !current.getElement().equals(element)) {
            current = current.getNext();
        }
        
        if (current == tail && !tail.getElement().equals(element)) {
            throw new ElementNotFoundException("Element not found!");
        }
        
        if (current == tail) {
            return removeLast();
        } else if (current == head) {
            return removeFirst();
        }
        
        T removed = current.getElement();
        
        current.getPrevious().setNext(current.getNext());
        current = current.getPrevious();
        
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
        
        DoubleNode<T> current = head;
        
        while (current != tail) {
            if (current.getElement().equals(target)) {
                return true;
            }
            
            current = current.getNext();
        }
        
        return tail.getElement().equals(target);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString() {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        String result = "";
        DoubleNode<T> current = head;
        
        while (current != tail) {
            result += " " + current.getElement();
            current = current.getNext();
        }
        
        result += " " + tail.getElement();
        
        return result;
    }
}
