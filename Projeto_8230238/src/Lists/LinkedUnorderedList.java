/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Stacks.LinearNode;

/**
 *
 * @author Miguel
 */
public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T> {
    
    /**
     * Constructor for LinkedUnorderedList
     */
    public LinkedUnorderedList() {
        super();
    }

    /**
     * Adds the specified element to the front of this list.
     *
     * @param element the element to be added to the front of this list
     */
    @Override
    public void addToFront(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        
        if (count == 0) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        
        count++;
        modCount++;
    }

    /**
     * Adds the specified element to the rear of this list.
     *
     * @param element the element to be added to the rear of this list
     */
    @Override
    public void addToRear(T element) {
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

    /**
     * Adds the specified element after the specified target.
     * 
     * @param element the element to add
     * @param target the element to add after
     * @throws ElementNotFoundException if the element was not found
     * @throws EmptyCollectionException if the collection was empty
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException, EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty Collection!");
        }
        
        LinearNode<T> current = head;

        while (current != null && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        
        if (current == null) {
            throw new ElementNotFoundException("Element not found!");
        }
        
        LinearNode<T> newNode = new LinearNode<>(element);
        
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        
        count++;
        modCount++;
    }
}
