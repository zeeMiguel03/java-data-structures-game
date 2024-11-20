/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Lists.ArrayUnorderedList;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */

public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {
    protected int height;
    protected int maxIndex;

    /**
     * Default constructor for Array Binary Search Tree
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Constructor for Array Binary Search, initializes with a single element
     * @param element the first element
     */
    public ArrayBinarySearchTree(T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    /**
     * Adds the specified object to this binary search tree in the
     * appropriate position according to its key value. Note that
     * equal elements are added to the right. Also note that the
     * index of the left child of the current index can be found by
     * doubling the current index and adding 1. Finding the index
     * of the right child can be calculated by doubling the current
     * index and adding 2.
     *
     * @param element the element to be added to the search tree
     */
    @Override
    public void addElement(T element) {        
        if (tree.length < maxIndex * 2 + 3) {
            expandCapacity();
        }
        
        Comparable<T> tempelement = (Comparable<T>)element;
        
        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            
            while (!added) {
                if (tempelement.compareTo((tree[currentIndex])) < 0) {
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        
                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                        
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        } 
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
       
        height = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }

    @Override
    public T removeElement(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("binary search tree");
        }

        Comparable<T> tempElement = (Comparable<T>) targetElement;

        int targetIndex = findIndex(tempElement, 0);
        
        if (targetIndex == -1) {
            throw new ElementNotFoundException("Element not found!");
        }

        T result = tree[targetIndex];
        replace(targetIndex);
        count--;

        int temp = maxIndex;
        maxIndex = -1;
        
        for (int i = 0; i <= temp; i++) {
            if (tree[i] != null) {
                maxIndex = i;
            }
        }

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;

        return result;
    }
    
    private int findIndex(Comparable<T> element, int root) {
        boolean found = false;
        int currentIndex = root;
        
        while (!found && tree[currentIndex] != null) {  
            if (element.compareTo((tree[currentIndex])) == 0) {
                return currentIndex;
            } else if (element.compareTo((tree[currentIndex])) < 0) {
                if (element.equals(currentIndex)) {
                    found = true;
                } else {
                    currentIndex = currentIndex * 2 + 1;
                }
            } else {
                if (element.equals(currentIndex)) {
                    found = true;
                } else {
                    currentIndex = currentIndex * 2 + 2;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Removes the node specified for removal and shifts the tree array accordingly.
     *
     * @param targetIndex the node to be removed
     */
    protected void replace(int targetIndex) {
        int currentIndex, oldIndex, newIndex;
        ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<>();
        Iterator<Integer> oldIt, newIt;

        /**
         * if target node has no children
         */
        if ((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length)) {
            tree[targetIndex] = null;
        } /**
            * if target node has no children
            */
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null)) {
            tree[targetIndex] = null;
        } /**
            * if target node only has a left child
            */
        else if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {
            /**
             * fill newlist with indices of nodes that will replace the corresponding indices in oldlist
             */
            currentIndex = targetIndex * 2 + 1;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                currentIndex = (templist.removeFirst());
                newlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * fill oldlist
             */
            currentIndex = targetIndex;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                currentIndex = (templist.removeFirst());
                oldlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * do replacement
             */
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();
                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } /**
            * if target node only has a right child
            */
        else if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {
            /**
             * fill newlist with indices of nodes that will replace the corresponding indices in oldlist
             */
            currentIndex = targetIndex * 2 + 2;
            templist.addToRear(currentIndex);
            while (!templist.isEmpty()) {
                currentIndex = (templist.removeFirst());
                newlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * fill oldlist
             */
            currentIndex = targetIndex;
            templist.addToRear(currentIndex);
            
            while (!templist.isEmpty()) {
                currentIndex = (templist.removeFirst());
                oldlist.addToRear(currentIndex);
                if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                    templist.addToRear(currentIndex * 2 + 1);
                    templist.addToRear(currentIndex * 2 + 2);
                }
            }

            /**
             * do replacement
             */
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
                oldIndex = oldIt.next();

                newIndex = newIt.next();
                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        } /**
            * if target node has two children
            */
        else {
            currentIndex = targetIndex * 2 + 2;

            while (tree[currentIndex * 2 + 1] != null) {
                currentIndex = currentIndex * 2 + 1;
            }

            tree[targetIndex] = tree[currentIndex];

            /**
             * the index of the root of the subtree to be replaced
             */
            int currentRoot = currentIndex;

            /**
             * if currentIndex has a right child
             */
            if (tree[currentRoot * 2 + 2] != null) {
                /**
                 * fill newlist with indices of nodes that will replace the corresponding indices in oldlist
                 */
                currentIndex = currentRoot * 2 + 2;
                templist.addToRear(currentIndex);
                while (!templist.isEmpty()) {
                    currentIndex = (templist.removeFirst());
                    newlist.addToRear(currentIndex);
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(currentIndex * 2 + 1);
                        templist.addToRear(currentIndex * 2 + 2);
                    }
                }

                /**
                 * fill oldlist
                 */
                currentIndex = currentRoot;
                templist.addToRear(currentIndex);
                while (!templist.isEmpty()) {
                    currentIndex = (templist.removeFirst());
                    oldlist.addToRear(currentIndex);
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2)) {
                        templist.addToRear(currentIndex * 2 + 1);
                        templist.addToRear(currentIndex * 2 + 2);
                    }
                }

                /**
                 * do replacement
                 */
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();

                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            } else {
                tree[currentRoot] = null;
            }
        }
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException {
        removeElement(targetElement);

        while (true) {
            try {
                removeElement(targetElement);
            } catch (EmptyCollectionException e) {
                break;
            }
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        return removeElement(findMin());
    }

    @Override
    public T removeMax() throws EmptyCollectionException {
        return removeElement(findMax());
    }

    //o findMin() e o findMax()
    @Override
    public T findMin() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection!");
        }
        
        int currentIndex = 0;

        while (currentIndex * 2 + 1 < maxIndex && tree[currentIndex * 2 + 1] != null) {
            currentIndex = currentIndex * 2 + 1;
        }
        
        return tree[currentIndex];
    }

    @Override
    public T findMax() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection!");
        }
          
        int currentIndex = 0;
        
        while (currentIndex * 2 + 2 <= maxIndex && tree[currentIndex * 2 + 2] != null) {
            currentIndex = currentIndex * 2 + 2;
        }
        
        return tree[currentIndex];
    }
    
    /**
     * This method extends the array by multiplying by two
     */
    private void expandCapacity() {
        T[] expand = (T[])(new Object[tree.length * 2]);

        for (int i = 0; i < tree.length; i++) {
            expand[i] = tree[i];
        }
        
        tree = expand; 
    }
}
