/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;

/**
 * @author Miguel Rocha
 */

public class SmackStack<T> extends ArrayStack<T> implements SmackStackADT<T> {
    
    /**
     * Creates an empty SmackStack with the default initial capacity.
     */
    public SmackStack() {
        super();
    }
    
    /**
     * Creates an SmackStack with a specific initial capacity.
     * @param initial the initial capacity.
     */
    public SmackStack(int initial) {
        super(initial);
    }

    /**
     * This method first verify if the collection is empty, if so
     * he throws a EmptyCollectionException, otherwise shift the elements
     * to the right positions and return the removed element.
     * 
     * @return the removed position
     * @throws EmptyCollectionException if the collection is empty
     */
    @Override
    public T smack() throws EmptyCollectionException {
        if (top == 0) {
            throw new EmptyCollectionException();
        }
        
        T element = stack[0];
        
        for (int i = 0; i < top - 1; i++) {
            stack[i] = stack[i + 1];
        }
        
        top--;
        return element;
    }
    
}
