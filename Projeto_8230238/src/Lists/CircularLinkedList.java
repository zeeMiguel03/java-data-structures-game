/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Stacks.LinearNode;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */
public class CircularLinkedList<T> implements ListADT<T> {
    private LinearNode<T> head;
    private LinearNode<T> tail;
    private int count;
    
    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }
    
    //funciona
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        
        if (count == 0) {
            head = tail = newNode;
            head.setNext(tail);
            tail.setNext(head);
        } else {
            newNode.setNext(head);
            tail.setNext(newNode);
            tail = newNode;
        }
        
        count++;
    }

    //funciona
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T element = head.getElement();
        
        head = head.getNext();
        tail.setNext(head);
        
        count--;
        
        return element;
    }

    //funciona
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T element = tail.getElement();
        
        LinearNode<T> current = head;
        
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        
        current.setNext(head);
        tail = current;
        
        count--;
        
        return element;
    }

    //funciona
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        LinearNode<T> current = head;
        LinearNode<T> previous = null;
        
        while (current != tail && !current.getElement().equals(element)) {
            previous = current;
            current = current.getNext();
        }
        
        if (current == tail && !tail.getElement().equals(element)) {
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

    //funciona
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        LinearNode<T> current = head;
        
        while (current != tail) {
            if (current.getElement().equals(target)) {
                return true;  
            }
            
            current = current.getNext();
        }
        
        if (tail.getElement().equals(target)) {
            return true;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString() {
        String result = "";
        
        LinearNode<T> current = head;
        
        while (current != tail) {
            result += " " + current.getElement();
            current = current.getNext();
        }
        
        result += " " + current.getElement();
        
        return result;
    }
}
