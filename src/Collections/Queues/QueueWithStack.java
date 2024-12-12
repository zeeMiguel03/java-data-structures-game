/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Stacks.LinkedStack;
import Collections.Stacks.StackADT;

/**
 *
 * @author Miguel
 */
public class QueueWithStack<T> implements QueueADT<T>{
    private StackADT<T> front;
    private StackADT<T> rear;
    private int count;
    
    public QueueWithStack() {
        this.front = new LinkedStack<>();
        this.rear = new LinkedStack<>();
        this.count = 0;
    }
    
    @Override
    public void enqueue(T element) {        
        rear.push(element);
        count++;
    }

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

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }
}
