/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;


/**
 * BinarySearchTreeADT defines the interface to a binary search tree.
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {
    
    /**
     * Adds the specified element to the proper location in this tree.
     *
     * @param element the element to be added to this tree
     */
   void addElement (T element);
   
   /**
    * Removes and returns the specified element from this tree.
    *
    * @param targetElement the element to be removed from this tree
    * @return the element removed from this tree
    */
   T removeElement (T targetElement) throws EmptyCollectionException, ElementNotFoundException;
   
   /**
    * Removes all occurences of the specified element from this tree.
    *
    * @param targetElement the element that the list will
    * have all instances of it removed
    */
   void removeAllOccurrences (T targetElement) throws EmptyCollectionException;
    
   /**
    * Removes and returns the smallest element from this tree.
    *
    * @return the smallest element from this tree.
    */
   T removeMin() throws EmptyCollectionException;
   
   /**
    * Removes and returns the largest element from this tree.
    *
    * @return the largest element from this tree
    */
   T removeMax() throws EmptyCollectionException;
   
   /**
    * Returns a reference to the smallest element in this tree.
    *
    * @return a reference to the smallest element in this tree
    */
   T findMin() throws EmptyCollectionException;
   
   /**
    * Returns a reference to the largest element in this tree.
    *
    * @return a reference to the largest element in this tree
    */
   T findMax() throws EmptyCollectionException;
}
