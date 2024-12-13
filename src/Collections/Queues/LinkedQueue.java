/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Stacks.LinearNode;

/**
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */
public class LinkedQueue<T> implements QueueADT<T> {
    private LinearNode<T> front, rear;
    private int count;
    
    /**
     * Creates an empty Queue.
     */
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.count = 0;
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to
     * the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode(element);
        
        if (front == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        
        count++;
    }

    /**
     * Removes and returns the element at the front of
     * this queue.
     *
     * @return the element at the front of this queue
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T removedNode = front.getElement(); 
        front = front.getNext();
        count--;
        
        return removedNode;
    }

    /**
     * Returns without removing the element at the front of
     * this queue.
     *
     * @return the first element in this queue
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        return front.getElement();
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size of this queue
     */
    @Override
    public int size() {
        return count;
    }
    
    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    @Override
    public String toString() {
        String list = "";
        LinearNode<T> current = front;
        
        while (current != null) {
            list += " " + current.getElement();
            current = current.getNext(); 
        }
        
        return list;
    }
}
