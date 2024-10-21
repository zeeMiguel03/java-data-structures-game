/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.NoComparableException;

/**
 *
 * @author Miguel
 */
public class DoubleLinkedOrderedList<T> extends DoublyLinkedList<T> implements OrderedListADT<T> {
    
    public DoubleLinkedOrderedList() {
        super();
    }
    
    @Override
    public void add(T element) throws NoComparableException { //tirar previ
        if (!(element instanceof Comparable)) {
            throw new NoComparableException("Element not Comparable!");
        }
        
        DoubleNode<T> newNode = new DoubleNode(element);
        
        if (count == 0) {
            head = tail = newNode;
        } else {
            DoubleNode<T> current = head;
            DoubleNode<T> previous = null;
            
            while (current != null && ((Comparable<T>) element).compareTo(current.getElement()) > 0) {
                previous = current;
                current = current.getNext();
            }
            
            if (previous == null) {
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else {
                newNode.setNext(current);
                newNode.setPrevious(previous);
                previous.setNext(newNode);
                if (current != null) {
                    current.setPrevious(newNode);
                } else {
                    tail = newNode;
                }
            }
        }
        
        count++;
        modCount++; 
    }
    
}
