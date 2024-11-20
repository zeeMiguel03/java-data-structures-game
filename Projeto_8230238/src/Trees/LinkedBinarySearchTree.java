/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

/**
 * @author Miguel Rocha
 */

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
    
    public LinkedBinarySearchTree() {
        super();
    }
    
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds the specified element to the proper location in this tree.
     *
     * @param element the element to be added to this tree
     */
    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        Comparable<T> comparableElement = (Comparable<T>)element;
        
        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            
            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) {
                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
        
        count++;
    }
    
    /**
     * Removes and returns the specified element from this tree.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree
     */
    @Override
    public T removeElement(T targetElement) throws EmptyCollectionException {
        T result = null;
        
        if (!isEmpty()) {
            if (((Comparable)targetElement).equals(root.element)) {
                result = root.element;
                root = replacement (root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;
                
                if (((Comparable)targetElement).compareTo(root.element) < 0) {
                    current = root.left;
                } else {
                    current = root.right;
                }
                
                while (current != null && !found) {
                    
                    if (targetElement.equals(current.element)) {
                        found = true;
                        count--;
                        result = current.element;
                    
                        if (current == parent.left) {
                            parent.left = replacement (current);
                        } else {
                            parent.right = replacement (current);
                        }
                    } else {
                        parent = current;
                        if (((Comparable)targetElement).compareTo(current.element) < 0) {
                            current = current.left;
                        } else {
                            current = current.right;
                        }
                    }
                } 
                
                if (!found) {
                     throw new ElementNotFoundException("Element not found!");
                }   
            } 
        }
        
        return result;
    }

    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result;
        
        if ((node.left == null) && (node.right == null)) {
            result = null;
        } else if ((node.left != null) && (node.right == null)) {
            result = node.left;
        } else if ((node.left == null) && (node.right != null)) {
            result = node.right;
        } else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            
            if (node.right == current) {
                current.left = node.left;
            } else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            
            result = current;
        }
        
        return result;
    }
    
    /**
     * Removes all occurences of the specified element from this tree.
     *
     * @param targetElement the element that the list will
     * have all instances of it removed
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException {        
        removeElement(targetElement);
        
        while (true) {
            try {
                removeElement(targetElement);
            } catch(EmptyCollectionException e) {
                break;
            }
        }
    }

    /**
     * Removes the lowest element.
     * @return the removed element
     * @throws EmptyCollectionException if the collection was empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        return removeElement(findMin());
    }

    /**
     * Removes the biggest element.
     * @return the removed element
     */
    @Override
    public T removeMax() throws EmptyCollectionException{
        return removeElement(findMax());
    }

    /**
     * Searchs for the min element in the tree.
     * 
     * @return the min element
     * @throws EmptyCollectionException if the collection was empty
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection!");
        }
        
        BinaryTreeNode<T> current = root;
        
        while (current.left != null) {
            current = current.left;
        }
        
        return current.element;
    }

    /**
     * Searchs for the max element in the tree.
     * 
     * @return the max element
     * @throws EmptyCollectionException if the collection was empty
     */
    @Override
    public T findMax() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection!");
        }
        
        BinaryTreeNode<T> current = root;
        
        while (current.right != null) {
            current = current.right;
        }
        
        return current.element;
    }
    
}
