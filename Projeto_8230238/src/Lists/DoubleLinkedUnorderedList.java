/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

/**
 *
 * @author Miguel
 */
public class DoubleLinkedUnorderedList<T> extends DoublyLinkedList<T> implements UnorderedListADT<T> {
    
    /**
     * Creates an new DoubleLinkedUnorderedList.
     */
    public DoubleLinkedUnorderedList() {
        super();
    }
    
    /**
     * This method adds a new element to the front of the list.
     * 
     * @param element the element to add.
     */
    @Override
    public void addToFront(T element) {
        DoubleNode<T> newNode = new DoubleNode(element);
        
        if (count == 0) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        
        count++;
        modCount++;
    }

    /**
     * This method adds a new element to the rear of the list.
     * 
     * @param element the element to add.
     */
    @Override
    public void addToRear(T element) {
        DoubleNode<T> newNode = new DoubleNode(element);
        
        if (count == 0) {
            head = tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        
        count++;
        modCount++;
    }

    //verificar se esta certo
    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, ElementNotFoundException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        DoubleNode<T> current = head;
        
        while (current != tail && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        
        if (current == tail && !tail.getElement().equals(target)) {
            throw new ElementNotFoundException("Element not found!");
        }
        
        if (current == tail) {
            addToRear(element);
        } else {
            DoubleNode<T> newNode = new DoubleNode(element);
            
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
            
            count++;
            modCount++;
        }
    }
}
