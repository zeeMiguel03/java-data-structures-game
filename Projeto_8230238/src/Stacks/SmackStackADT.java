/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Stacks;

import Exceptions.EmptyCollectionException;

/**
 *
 * @author Miguel
 */
public interface SmackStackADT<T> extends StackADT<T> {
    
    /**
     * Removes the last element in a stack
     *
     * @return the removed element
     * @throws EmptyCollectionException if the collection was empty
     */
    public T smack() throws EmptyCollectionException;
    
}
