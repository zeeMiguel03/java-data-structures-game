/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import Exceptions.NoComparableException;

/**
 *
 * @author Miguel
 */
public class ArrayOrderedList<T> extends DefaultArrayList<T> implements OrderedListADT<T> {
    
    public ArrayOrderedList() {
        super();
    }
    
    public ArrayOrderedList(int initial) {
        super(initial);
    }

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
