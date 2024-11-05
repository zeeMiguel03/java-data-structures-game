/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.NoComparableException;

//Está tudo a funcionar e tudo corrigido!

/**
 * @author Miguel
 */
public class ArrayOrderedList<T> extends DefaultArrayList<T> implements OrderedListADT<T> {
    
    /**
     * Creates an empty ArrayOrderedList with the default initial capacity.
     */
    public ArrayOrderedList() {
        super();
    }
    
    /**
     * Creates an ArrayOrderedList with a specific initial capacity.
     * @param initial the initial capacity.
     */
    public ArrayOrderedList(int initial) {
        super(initial);
    }

    /**
     * This method starts by checking if the array is full, if so, the space increases,
     * then search in the array the right position to put the element, then
     * shift the elements to the right positions and add the new element.
     * 
     * @param element the element to insert in the array
     * @throws NoComparableException if the element isn't comparable
     */
    @Override
    public void add(T element) throws NoComparableException {
        if (!(element instanceof Comparable)) {
            throw new NoComparableException("Element not Comparable!");
        }
        
        if (count == ArrayList.length) {
            expandCapacity();
        }
        
        int counter = 0;
        
        while (counter < count && ((Comparable<T>) element).compareTo(ArrayList[counter]) > 0) {
            counter++;
        }
        
        for (int i = count; i > counter; i--) {
            ArrayList[i] = ArrayList[i - 1];
        }  
        
        ArrayList[counter] = element;
        count++;
        modCount++;
    }
}
