/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.ElementNotFoundException;

/**
 *
 * @author Miguel
 */
public class ArrayUnorderedList<T> extends DefaultArrayList<T> implements UnorderedListADT<T> {
    
    /**
     * Creates an empty ArrayUnorderedList with the default initial capacity.
     */
    public ArrayUnorderedList() {
        super();
    }
    
    /**
     * Creates an ArrayUnorderedList with a specific initial capacity.
     * @param initial the initial capacity.
     */
    public ArrayUnorderedList(int initial) {
        super(initial);
    }

    /**
     * This method starts by checking if the array is full, if so, the space increases,
     * then he shifts the elements to the right place, and inserts 
     * the new element in front.
     * 
     * @param element the element to insert in front
     */
    @Override
    public void addToFront(T element) {
        if (count == ArrayList.length) {
            expandCapacity();
        }
        
        for (int i = count; i > 0; i--) {
            ArrayList[i] = ArrayList[i - 1];
        }
    
        ArrayList[0] = element;
        count++;
        modCount++;
    }

    /**
     * This method starts by checking if the array is full, if so, the space increases,
     * then add's a new element to the end of the ArrayList.
     * 
     * @param element the element to insert in rear
     */
    @Override
    public void addToRear(T element) {
        if (count == ArrayList.length) {
            expandCapacity();
        }
        
        ArrayList[count++] = element;
        
        modCount++;
    }

    /**
     * This method strats by checking if the array is full, if so, the space increases,
     * then search for the target, if the target was not found, it throws a an
     * ElementNotFoundException, otherwise if the target was found the method shifts
     * the elements to the right place, and inserts the new element.
     * 
     * @param element the element to insert in the ArrayList
     * @param target the element after which the new element is to be inserted
     * @throws ElementNotFoundException  if the target element is not found in the list
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException{
        if (count == ArrayList.length) {
            expandCapacity();
        }
        
        int counter = 0;
        
        while (counter < count && !ArrayList[counter].equals(target)) {
            counter++;
        }
        
        if (ArrayList[counter] == null) {
            throw new ElementNotFoundException("Element not founded!");
        }

        for (int i = count; i > counter; i--) {
            ArrayList[i] = ArrayList[i - 1];
        }  
        
        ArrayList[counter + 1] = element;
        count++;
        modCount++;
    }
}
