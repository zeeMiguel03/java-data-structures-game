/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;

/**
 *
 * @author Miguel
 */
public class DoubleLinkedUnorderedList<T> extends DoublyLinkedList<T> implements UnorderedListADT<T> {
    
    public DoubleLinkedUnorderedList() {
        super();
    }
    
    //Funciona
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

    //funciona
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

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        DoubleNode<T> newNode = new DoubleNode(element);
        
        if (head.getElement().equals(target)) {
            newNode.setPrevious(head);
            newNode.setNext(head.getNext());
            head.setNext(newNode);
        } else if (tail.getElement().equals(target)) {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        } else {
            DoubleNode<T> current = head;
            
            while (current != null && !current.getElement().equals(target)) {
                current = current.getNext();
            }
            
            if (current == null) {
                throw new ElementNotFoundException("Element not found!");
            }
            
            newNode.setPrevious(current);
   
            if (current != null) {
                newNode.setNext(current.getNext());
                current.setNext(newNode);
            } 
        }
        
    }
    
}
