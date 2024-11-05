/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stacks;

import Exceptions.EmptyCollectionException;

/**
 *
 * @author Miguel
 */
public class ArrayStack<T> implements StackADT<T> {
    private static final int INITAL_CAPACITY = 100;
    
    protected int top;
    protected T[] stack;
    
    /**
    * Creates an empty stack using the default capacity.
    */
    public ArrayStack() {
        this.stack = (T[])(new Object[INITAL_CAPACITY]);
        this.top = 0;
    }
    
    /**
    * Creates an empty stack using the specified capacity.
    * @param initial represents the specified capacity
    */
    public ArrayStack(int initial) {
        this.stack = (T[])(new Object[initial]);
        this.top = 0;
    }

    /**
    * Adds the specified element to the top of this stack,
    * expanding the capacity of the stack array if necessary.
    * @param element generic element to be pushed onto stack
    */
    @Override
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }
        
        stack[top++] = element;
    }

    /**
    * Removes the element at the top of this stack and
    * returns a reference to it.
    * 
    * Throws an EmptyCollectionException if the stack is empty.
    * @return T element removed from top of stack
    * @throws EmptyCollectionException if a pop
    * is attempted on empty stack
    */
    @Override
    public T pop() throws EmptyCollectionException {  
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        
        top--;
        T result = stack[top];
        stack[top] = null;
        
        return result;
    }

    /**
    * Returns a reference to the element at the top of this stack.
    * The element is not removed from the stack.
    * 
    * Throws an EmptyCollectionException if the stack is empty.
    * @return T element on top of stack
    * @throws EmptyCollectionException if a
    * peek is attempted on empty stack
    */
    @Override
    public T peek() throws EmptyCollectionException  {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        
        return stack[top - 1];
    }

    /**
     * Returns true if the array is empty, false otherwise
     * @return true if the array is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the size of the array
     * @return array size
     */
    @Override
    public int size() {
        return top;
    }
    
    @Override
    public String toString() {
        String result = "";
        
        for (int i = 0; i < top; i++) {
            result += " " + stack[i];
        }
        
        return result;
    }
    
    /**
     * Expands the capacity of the stack by creating a new array with 
     * double the size, and copies the elements 
     * from the old stack to the new expanded stack.
     */
    private void expandCapacity() {
        T[] expand = (T[])(new Object[stack.length * 2]);
        
        for (int i = 0; i < top; i++) {
            expand[i] = stack[i];
        }
        
        stack = expand;
    }
}