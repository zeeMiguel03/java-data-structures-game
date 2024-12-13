/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Stacks.LinkedStack;
import Collections.Stacks.StackADT;

/**
 * Author: António Miguel Cunha Monteiro
 * Number: 8230230
 *
 * Author: José Miguel Monteiro da Rocha
 * Number: 8230238
 */

public class QueueWithStack<T> implements QueueADT<T>{
    private StackADT<T> front;
    private StackADT<T> rear;
    private int count;

    /**
     * Constructor for QueueWithStack class
     */
    public QueueWithStack() {
        this.front = new LinkedStack<>();
        this.rear = new LinkedStack<>();
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
        rear.push(element);
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
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        while (!rear.isEmpty()) {
            front.push(rear.pop());
        }
        
        T removedNode = front.pop();
        count--;
        
        while (!front.isEmpty()) {
            rear.push(front.pop());
        }
        
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
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        while (!rear.isEmpty()) {
            front.push(rear.pop());
        }
        
        T peek = front.peek();
        
        while (!front.isEmpty()) {
            rear.push(front.pop());
        }
        
        return peek;
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
     * @return the integer representation of the size
     * of this queue
     */
    @Override
    public int size() {
        return count;
    }
}
