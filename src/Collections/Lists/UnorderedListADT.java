/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Collections.Lists;

import Collections.Exceptions.ElementNotFoundException;

public interface UnorderedListADT<T> extends ListADT<T>{
    
    /**
     * Adds the specified element to the front of this list.
     *
     * @param element the element to be added to the front of this list
     */
    void addToFront(T element);
    
    /**
     * Adds the specified element to the rear of this list.
     *
     * @param element the element to be added to the rear of this list
     */
    void addToRear(T element);
    
    /**
     * Adds the specified element after the specified target.
     *
     * @param element the element to be added after the target
     * @param target the target is the item that the element will be added after
     */
    void addAfter(T element, T target) throws ElementNotFoundException;
}
