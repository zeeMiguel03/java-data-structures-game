/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;

/**
 * @author Miguel Rocha
 */
public class LinkedStack<T> implements StackADT<T> {
    private LinearNode<T> top;
    private int size;

    /**
    * Creates an empty stack.
    */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }
    
    /**
    * Adds the specified element to the top of this stack,
    * @param element generic element to be pushed onto stack
    */
    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode(element);
        
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    /**
    * Removes the element at the top of this stack and
    * returns a reference to it.
    * Throws an EmptyCollectionException if the stack is empty.
    * @return T element removed from top of stack
    * @throws EmptyCollectionException if a pop
    * is attempted on empty stack
    */
    @Override
    public T pop() throws EmptyCollectionException {
        if (top == null) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        T removedNode = top.getElement();
        top = top.getNext();
        size--;
        
        return removedNode;
    }

    /**
    * Returns a reference to the element at the top of this stack.
    * The element is not removed from the stack.
    * Throws an EmptyCollectionException if the stack is empty.
    * @return T element on top of stack
    * @throws EmptyCollectionException if a
    * peek is attempted on empty stack
    */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list!");
        }
        
        return top.getElement();
    }

     /**
     * Returns true if the array is empty, false otherwise
     * @return true if the array is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the array
     * @return array size
     */
    @Override
    public int size() {
        return size;
    }   
    
    /** 
     * Returns a string representation of this stack.
     * @return String representation of this stack
     */
    @Override
    public String toString() {
        String list = "";
        LinearNode<T> current = top;
        
        while (current != null) {
            list += " " + current.getElement();
            current = current.getNext(); 
        }
        
        return list;
    }
}
