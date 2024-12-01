/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;

/**
 * @author Miguel Rocha
 */
public class CircularArrayQueue<T> implements QueueADT<T> {
    private static final int INITAL_CAPACITY = 100;
    private int front, rear, count;
    private T[] queue;
    
    /**
    * Creates an empty queue using the default capacity.
    */
    public CircularArrayQueue() {
        this.queue = (T[]) (new Object[INITAL_CAPACITY]);
        this.front = this.rear = this.count = 0;
    }
    
    /**
    * Creates an empty queue using the specified capacity..
     * @param initial
    */
    public CircularArrayQueue(int initial) {
        this.queue = (T[]) (new Object[initial]);
        this.front = this.rear = this.count = 0;
    }

    /**
    * Adds one element to the rear of this queue.
    *
    * @param element the element to be added to
    * the rear of this queue
    */
    @Override
    public void enqueue(T element) {
        if (count == queue.length) {
            expandCapacity();
        }
        
        queue[rear] = element;
        rear = (rear + 1) % queue.length;
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
        
        T remove = queue[front];
        
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;
        
        return remove;
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
        
        return queue[front];
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
    
    /**
    * Returns a string representation of this queue.
    *
    * @return the string representation of this queue
    */
    @Override
    public String toString() {
        String list = "";
        int current = front;
        
        while (current != rear) {
            list += " " + queue[current];
            current++;
        }
        
        return list;
    }
    
    /**
    * Expands the capacity of the queue by creating a new array with 
    * double the size of the capacity, and copies the elements 
    * from the old queue to the new expanded queue.
    */
    public void expandCapacity() {
        T[] expand = (T[])(new Object[count * 2]);
        
        for (int i = 0; i < count; i++) {
            expand[i] = queue[i];
        }
        
        queue = expand;
    }
}
