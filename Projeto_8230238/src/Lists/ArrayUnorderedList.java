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
    
    public ArrayUnorderedList() {
        super();
    }
    
    public ArrayUnorderedList(int initial) {
        super(initial);
    }

    @Override
    public void addToFront(T element) {
        if (count == ArrayList.length) {
            expandCapacity();
        }
        
        if (count == 0) {
            ArrayList[0] = element;
        } else {
            for (int i = count; i > 0; i--) {
                ArrayList[i] = ArrayList[i - 1];
            }  
            
            ArrayList[0] = element;
        }
        
        count++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (count == ArrayList.length) {
            expandCapacity();
        }
        
        ArrayList[count++] = element;
        
        modCount++;
    }

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
