/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

/**
 *
 * @author Miguel
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
    
    public LinkedBinarySearchTree() {
        super();
    }
    
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

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
                     throw new ElementNotFoundException("binary search tree");
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
    
    @Override
    public void removeAllOccurrences(T targetElement) {
        removeElement(targetElement);
        
        while (contains(targetElement)) {
            removeElement(targetElement);
        }   
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        return removeElement(findMin());
    }

    @Override
    public T removeMax() {
        return removeElement(findMax());
    }

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
