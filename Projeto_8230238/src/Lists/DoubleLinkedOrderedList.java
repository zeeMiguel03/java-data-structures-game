/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.NoComparableException;

/**
 * @author Miguel Rocha
 */
public class DoubleLinkedOrderedList<T> extends DoublyLinkedList<T> implements OrderedListADT<T> {
    
    /**
     * Creates a new DoubleLinkedOrderedList.
     */
    public DoubleLinkedOrderedList() {
        super();
    }
    
    /**
     * This method starts by verifying if the element to be added is comparable,
     * If it is not, it throws a NoComparableException. Then, it performs the necessary
     * operations to determine the correct position for the new element.
     * 
     * @param element the element to add.
     * @throws NoComparableException if the element isn´t comparable.
     */
    @Override
    public void add(T element) throws NoComparableException {
        if (!(element instanceof Comparable)) {
            throw new NoComparableException("Element not Comparable!");
        }

        DoubleNode<T> newNode = new DoubleNode<>(element);

        if (count == 0) {
            head = tail = newNode;
        } else {
            DoubleNode<T> current = head;

            while (current != null && ((Comparable<T>) element).compareTo(current.getElement()) > 0) {
                current = current.getNext();
            }

            if (current == head) { 
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else if (current == null) { 
                tail.setNext(newNode);
                newNode.setPrevious(tail);
                tail = newNode;
            } else { 
                newNode.setNext(current);
                newNode.setPrevious(current.getPrevious());
                current.getPrevious().setNext(newNode);
                current.setPrevious(newNode);
            }
        }

        count++;
        modCount++; 
    }
}
