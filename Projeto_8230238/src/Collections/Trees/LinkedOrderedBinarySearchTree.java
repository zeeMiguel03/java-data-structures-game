/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoComparableException;
import Collections.Lists.OrderedListADT;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */
public class LinkedOrderedBinarySearchTree<T> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {
    
    public LinkedOrderedBinarySearchTree() {
        super();
    }
    
    public LinkedOrderedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void add(T element) throws NoComparableException {
        if (!(element instanceof Comparable)) {
            throw new NoComparableException("Element not comparable!");
        }
        
        Comparable<T> comparableElement = (Comparable<T>) element;
        
        BinaryTreeNode<T> newNode = new BinaryTreeNode<>(element);
        
        if (count == 0) {
            root = newNode;
        } else {
            BinaryTreeNode<T> current = root;
            BinaryTreeNode<T> parent = null;
            
            while (current != null) {
                parent = current;
                
                if (comparableElement.compareTo(current.getElement()) < 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
            
            if (comparableElement.compareTo(parent.getElement()) < 0) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
        }
        
        count++;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        return removeMin();
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection");
        }
        
        return removeMax();
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        return removeElement(element);
    }

    @Override
    public T first() throws EmptyCollectionException {
        return findMin();
    }

    @Override
    public T last() throws EmptyCollectionException {
        return findMax();
    }

    @Override
    public Iterator<T> iterator() {
        return iteratorInOrder();
    }  
}
